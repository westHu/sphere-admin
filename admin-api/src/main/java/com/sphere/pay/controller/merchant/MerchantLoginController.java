package com.sphere.pay.controller.merchant;

import cn.hutool.json.JSONUtil;
import com.sphere.pay.MerchantRequestManager;
import com.sphere.pay.config.limit.LimitFlag;
import com.sphere.pay.config.limit.LimitTypeEnum;
import com.sphere.pay.config.login.LoginLogFlag;
import com.sphere.pay.config.opt.OptLogFlag;
import com.sphere.pay.controller.request.MerchantLoginReq;
import com.sphere.pay.convert.MerchantLoginConverter;
import com.sphere.pay.dto.MerchantLoginDTO;
import com.sphere.pay.dto.token.TokenMerchantOperatorDTO;
import com.sphere.pay.enums.LogLoginTypeEnum;
import com.sphere.pay.param.MerchantLoginParam;
import com.sphere.pay.remote.merchant.MerchantApiService;
import com.sphere.pay.remote.merchant.param.MerchantLoginAuthenticatorExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantLoginAuthenticatorSetExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantLoginAuthenticatorUnsetExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantVerifyAuthCodeExchangeParam;
import com.sphere.pay.result.Result;
import com.sphere.pay.service.merchant.MerchantLoginService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/merchant")
public class MerchantLoginController {

    @Resource
    MerchantLoginConverter merchantLoginConverter;
    @Resource
    MerchantLoginService merchantLoginService;
    @Resource
    MerchantApiService merchantApiService;

    /**
     * 商户登录
     */
    @LoginLogFlag(type = LogLoginTypeEnum.MERCHANT_LOGIN)
    @LimitFlag(limitType = LimitTypeEnum.IP)
    @PostMapping("/merchantLogin")
    public Mono<Result<MerchantLoginDTO>> merchantLogin(@RequestBody @Validated MerchantLoginReq req) {
        log.info("smilepayz merchantLogin req={}", JSONUtil.toJsonStr(req));
        MerchantLoginParam param = merchantLoginConverter.toMerchantLoginParam(req);
        MerchantLoginDTO dto = merchantLoginService.merchantLogin(param);
        return Mono.justOrEmpty(Result.ok(dto));
    }

    /**
     * 展示谷歌验证器验证码
     */
    @LimitFlag(limitType = LimitTypeEnum.MERCHANT_ID)
    @PostMapping("/getLoginAuthenticator")
    public Mono<Result<String>> getLoginAuthenticator(ServerWebExchange exchange) {
        TokenMerchantOperatorDTO operator = MerchantRequestManager.getCurrentMerchantOperator();
        MerchantLoginAuthenticatorExchangeParam param = new MerchantLoginAuthenticatorExchangeParam();
        param.setMerchantId(operator.getMerchantId());
        param.setUsername(operator.getUsername());
        log.info("getLoginAuthenticator param={}", JSONUtil.toJsonStr(param));
        return merchantApiService.getLoginAuthenticator(param);
    }

    /**
     * 绑定谷歌验证器
     */
    @OptLogFlag
    @LimitFlag(limitType = LimitTypeEnum.MERCHANT_ID)
    @PostMapping("/setLoginAuthenticator")
    public Mono<Result<Boolean>> setLoginAuthenticator(@RequestBody @Validated ServerWebExchange exchange,
                                                       MerchantLoginAuthenticatorSetExchangeParam param) {
        TokenMerchantOperatorDTO currentMerchantOperator = MerchantRequestManager.getCurrentMerchantOperator();
        param.setMerchantId(currentMerchantOperator.getMerchantId());
        param.setUsername(currentMerchantOperator.getUsername());
        return merchantApiService.setLoginAuthenticator(param);
    }

    /**
     * 解绑谷歌验证器
     */
    @OptLogFlag
    @PostMapping("/unsetLoginAuthenticator")
    public Mono<Result<Boolean>> unbindLoginAuth(@RequestBody @Validated ServerWebExchange exchange,
                                           MerchantLoginAuthenticatorUnsetExchangeParam param) {
        TokenMerchantOperatorDTO currentMerchantOperator = MerchantRequestManager.getCurrentMerchantOperator();
        param.setMerchantId(currentMerchantOperator.getMerchantId());
        param.setUsername(currentMerchantOperator.getUsername());
        return merchantApiService.unsetLoginAuthenticator(param);
    }

    /**
     * 验证谷歌验证码
     */
    @OptLogFlag
    @PostMapping("/verifyLoginAuthenticator")
    public Mono<Result<Boolean>> verifyLoginAuthenticator(@RequestBody @Validated ServerWebExchange exchange,
                                                          MerchantVerifyAuthCodeExchangeParam param) {
        log.info("smilepayz verifyLoginAuthenticator param={}", JSONUtil.toJsonStr(param));
        TokenMerchantOperatorDTO currentMerchantOperator = MerchantRequestManager.getCurrentMerchantOperator();
        param.setUsername(currentMerchantOperator.getUsername());
        return merchantApiService.verifyLoginAuthenticator(param);
    }

    /**
     * 商户登出
     */
    @LoginLogFlag(type = LogLoginTypeEnum.MERCHANT_LOGOUT)
    @PostMapping("/merchantLogout")
    public Mono<Result<Boolean>> merchantLogout(ServerWebExchange exchange) {
        log.info("merchantLogout time={}", LocalDateTime.now());
        return Mono.just(Result.ok(merchantLoginService.logout(exchange)));
    }
}
