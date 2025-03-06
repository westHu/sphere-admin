package com.sphere.pay.service.impl.merchant;

import cn.hutool.core.lang.Assert;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentInfo;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWTUtil;
import com.sphere.pay.AdminConstant;
import com.sphere.pay.IpManager;
import com.sphere.pay.assembler.ApplicationConverter;
import com.sphere.pay.db.entity.MerchantLoginLog;
import com.sphere.pay.dto.MerchantLockLogDTO;
import com.sphere.pay.dto.MerchantLoginDTO;
import com.sphere.pay.enums.LogLoginTypeEnum;
import com.sphere.pay.exception.AdminException;
import com.sphere.pay.param.MerchantLockParam;
import com.sphere.pay.param.MerchantLoginParam;
import com.sphere.pay.remote.merchant.MerchantApiService;
import com.sphere.pay.remote.merchant.dto.MerchantBaseExchangeDTO;
import com.sphere.pay.remote.merchant.dto.MerchantLoginExchangeDTO;
import com.sphere.pay.remote.merchant.dto.MerchantOperatorExchangeDTO;
import com.sphere.pay.remote.merchant.param.MerchantLoginExchangeParam;
import com.sphere.pay.result.BaseResult;
import com.sphere.pay.result.Result;
import com.sphere.pay.service.merchant.MerchantLockLogService;
import com.sphere.pay.service.merchant.MerchantLoginLogService;
import com.sphere.pay.service.merchant.MerchantLoginService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class MerchantLoginServiceImpl implements MerchantLoginService {

    @Value("${spring.profiles.active}")
    String profile;

    @Resource
    MerchantApiService merchantApiService;

    @Resource
    ApplicationConverter applicationConverter;
    @Resource
    MerchantLockLogService merchantLockLogService;
    @Resource
    MerchantLoginLogService merchantLoginLogService;


    /**
     * 查看是否锁住 -> 登录
     */
    @Override
    public MerchantLoginDTO merchantLogin(MerchantLoginParam param) {
        //解密密码
        String key = "13s4m67a12245f79";
        AES aes = new AES(Mode.ECB, Padding.PKCS5Padding, key.getBytes());
        String decrypt = aes.decryptStr(param.getPassword());
        param.setPassword(decrypt);

        //校验是否被锁住
        MerchantLockParam lockParam = new MerchantLockParam();
        lockParam.setLockName(param.getUsername());
        MerchantLockLogDTO lockLogDTO = merchantLockLogService.getMerchantLockLog(lockParam);
        if (Objects.nonNull(lockLogDTO)) {
            throw new AdminException("锁住了");
        }

        //调用
        MerchantLoginExchangeParam exchangeParam = new MerchantLoginExchangeParam();
        exchangeParam.setUsername(param.getUsername());
        exchangeParam.setPassword(param.getPassword());
        Result<MerchantLoginExchangeDTO> result = merchantApiService.merchantLogin(exchangeParam).toFuture().join();
        MerchantLoginExchangeDTO loginExchangeDTO = BaseResult.parse(result);
        Assert.notNull(loginExchangeDTO, () -> new AdminException("登录失败"));

        //生成token
        MerchantBaseExchangeDTO baseExchangeDTO = loginExchangeDTO.getMerchant();
        MerchantOperatorExchangeDTO operatorExchangeDTO = loginExchangeDTO.getMerchantOperator();

        Map<String, Object> headers = new HashMap<>();
        headers.put(AdminConstant.LOGIN_CLAIM_LOGIN_FROM, "M");

        Map<String, Object> payload = new HashMap<>();
        payload.put(AdminConstant.LOGIN_EXPIRES_AT, JSONUtil.toJsonStr(baseExchangeDTO));
        payload.put(AdminConstant.LOGIN_CLAIM_MERCHANT, JSONUtil.toJsonStr(baseExchangeDTO));
        payload.put(AdminConstant.LOGIN_CLAIM_OPERATOR, JSONUtil.toJsonStr(operatorExchangeDTO));
        String token = JWTUtil.createToken(headers, payload, "2222".getBytes());

        MerchantLoginDTO loginDTO = new MerchantLoginDTO();
        loginDTO.setToken(token);
        loginDTO.setMerchant(baseExchangeDTO);
        loginDTO.setMerchantOperator(operatorExchangeDTO);
        loginDTO.setChangePassword(false);
        return loginDTO;
    }


    @Override
    public boolean logout(ServerWebExchange exchange) {
        return true;
    }


    //----------------------------------------------------------------------------------------------------


    /**
     * 获取浏览器 浏览器版本
     */
    private String getBrowserName(ServerWebExchange exchange) {
        // 获取 User-Agent 头信息
        String uaStr = exchange.getRequest().getHeaders().getFirst("User-Agent");

        // 解析 User-Agent
        UserAgent ua = UserAgentUtil.parse(uaStr);

        // 使用 Optional 避免空指针异常
        String browser = Optional.ofNullable(ua)
                .map(UserAgent::getBrowser)
                .map(UserAgentInfo::toString)
                .orElse("");
        String version = Optional.ofNullable(ua)
                .map(UserAgent::getVersion)
                .orElse("");

        // 返回结果
        return browser + ":" + version;
    }


    /**
     * 获取操作系统名称
     */
    private String getOsName(ServerWebExchange exchange) {
        String uaStr = exchange.getRequest().getHeaders().getFirst("User-Agent") != null ? exchange.getRequest().getHeaders().getFirst("User-Agent") : "Unknown";
        UserAgent ua = UserAgentUtil.parse(uaStr);
        String os = (ua != null && ua.getOs() != null) ? ua.getOs().toString() : "";
        return os.length() > 31 ? os.substring(0, 31) : os;
    }

    /**
     * 保持登录信息
     */
    private void saveLoginLog(String loginName, LogLoginTypeEnum logLoginTypeEnum, ServerWebExchange exchange) {
        //浏览器 & 操作系统
        String browserInfo = getBrowserName(exchange);
        String osInfo = getOsName(exchange);
        String ip = IpManager.getIp(exchange);
        log.info("LoginLogAspect loginName={}, browserInfo={}, osInfo={}", loginName, browserInfo, osInfo);

        MerchantLoginLog loginLog = new MerchantLoginLog();
        loginLog.setLoginName(loginName);
        loginLog.setLoginType(logLoginTypeEnum.getCode());
        loginLog.setLoginIp(ip);
        loginLog.setLoginStatus(true);
        loginLog.setLoginTime(LocalDateTime.now());
        loginLog.setLoginBrowserInfo(browserInfo);
        loginLog.setLoginOsInfo(osInfo);
        merchantLoginLogService.save(loginLog);
    }

}
