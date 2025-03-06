package com.sphere.pay.controller.sys;


import cn.hutool.json.JSONUtil;
import com.sphere.pay.SysRequestManager;
import com.sphere.pay.config.login.LoginLogFlag;
import com.sphere.pay.config.opt.OptLogFlag;
import com.sphere.pay.controller.request.IdReq;
import com.sphere.pay.controller.request.LoginChangePwdReq;
import com.sphere.pay.controller.request.SysLoginReq;
import com.sphere.pay.convert.SysLoginConverter;
import com.sphere.pay.dto.SysLoginDTO;
import com.sphere.pay.dto.SysResourceDTO;
import com.sphere.pay.dto.SysUserInfoDTO;
import com.sphere.pay.dto.token.TokenUserDTO;
import com.sphere.pay.enums.LogLoginTypeEnum;
import com.sphere.pay.param.LoginResetPwdParam;
import com.sphere.pay.param.SysLoginParam;
import com.sphere.pay.result.Result;
import com.sphere.pay.service.sys.SysLoginService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录API
 *
 * @author dh
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class SysLoginController {

    @Resource
    SysLoginService sysLoginService;
    @Resource
    SysLoginConverter sysLoginConverter;

    /**
     * 登录
     */
    @LoginLogFlag(type = LogLoginTypeEnum.SYS_LOGIN)
    @PostMapping("/auth/login")
    public Mono<Result<SysLoginDTO>> sysLogin(@RequestBody @Validated SysLoginReq req, ServerWebExchange exchange) {
        log.info("sysLogin req={}", JSONUtil.toJsonStr(req));
        SysLoginParam param = sysLoginConverter.convertSysLoginParam(req);
        SysLoginDTO sysLoginDTO = sysLoginService.sysLogin(param, exchange);
        return Mono.just(Result.ok(sysLoginDTO));
    }

    /**
     * 登录用户信息
     */
    @GetMapping("/user/info")
    public Mono<Result<SysUserInfoDTO>> getSysUserInfo(ServerWebExchange exchange) {
        TokenUserDTO currentUser = SysRequestManager.getCurrentUser();
        log.info("getSysUserInfo currentUser={}", JSONUtil.toJsonStr(currentUser));
        SysUserInfoDTO userInfoDTO = sysLoginService.getSysUserInfo(currentUser, exchange);
        return Mono.just(Result.ok(userInfoDTO));
    }

    /**
     * 登录权限信息
     */
    @GetMapping("/auth/codes")
    public Mono<Result<List<String>>> sysAuthCodes() {
        return Mono.just(Result.ok(new ArrayList<>()));
    }

    /**
     * 登出
     */
    @LoginLogFlag(type = LogLoginTypeEnum.SYS_LOGOUT)
    @PostMapping("/auth/logout")
    public Mono<Result<Void>> sysLogout(ServerWebExchange exchange) {
        log.info("sysLogout time={}", LocalDateTime.now());
        sysLoginService.sysLogout(exchange);
        return Mono.just(Result.ok());
    }

    /**
     * 查询菜单
     */
    @PostMapping("/getSysMenu")
    public Mono<Result<List<SysResourceDTO>>> getSysMenu(ServerWebExchange exchange) {
        log.info("getSysMenu");
        List<SysResourceDTO> dtoList = sysLoginService.getSysMenu(exchange);
        return Mono.just(Result.ok(dtoList));
    }

    /**
     * 修改密码
     */
    @OptLogFlag
    @PostMapping("/changeSysPwd")
    public Mono<Result<Void>> changeSysPwd(@RequestBody @Validated LoginChangePwdReq req, ServerWebExchange exchange) {
        log.info("changeSysPwd req={}", JSONUtil.toJsonStr(req));
        TokenUserDTO currentUser = SysRequestManager.getCurrentUser();
        LoginResetPwdParam param = sysLoginConverter.convertLoginResetPwdParam(req);
        param.setUsername(currentUser.getUserName());
        sysLoginService.changeSysPwd(param);
        return Mono.just(Result.ok());
    }

    /**
     * 重置密码
     */
    @OptLogFlag
    @PostMapping("/resetSysPwd")
    public Mono<Result<Boolean>> resetSysPwd(@RequestBody @Validated IdReq req) {
        log.info("resetSysPwd req={}", JSONUtil.toJsonStr(req));
        sysLoginService.resetSysPwd(req.getId());
        return Mono.just(Result.ok());
    }

}
