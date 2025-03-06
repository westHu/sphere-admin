package com.sphere.pay.controller.sys;

import cn.hutool.json.JSONUtil;
import com.sphere.pay.config.opt.OptLogFlag;
import com.sphere.pay.config.valid.PasswordValid;
import com.sphere.pay.controller.request.GoogleCodeUnBindReq;
import com.sphere.pay.controller.request.GoogleCodeVerifyReq;
import com.sphere.pay.controller.request.IdReq;
import com.sphere.pay.controller.request.SysUserAddReq;
import com.sphere.pay.controller.request.SysUserPageReq;
import com.sphere.pay.controller.request.SysUserUpdateReq;
import com.sphere.pay.convert.SysRoleConverter;
import com.sphere.pay.convert.SysUserConverter;
import com.sphere.pay.dto.AuthShowDTO;
import com.sphere.pay.dto.SysUserDTO;
import com.sphere.pay.param.AuthCodeVerifyParam;
import com.sphere.pay.param.SysUserAddParam;
import com.sphere.pay.param.SysUserPageParam;
import com.sphere.pay.param.SysUserUpdateParam;
import com.sphere.pay.result.PageResult;
import com.sphere.pay.result.Result;
import com.sphere.pay.service.sys.SysRoleService;
import com.sphere.pay.service.sys.SysUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * 用户API
 *
 * @author west
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class SysUserController {

    @Resource
    SysUserService sysUserService;
    @Resource
    SysRoleService sysRoleService;
    @Resource
    SysUserConverter sysUserConverter;
    @Resource
    SysRoleConverter sysRoleConverter;

    /**
     * 用户列表
     */
    @PostMapping("/pageSysUserList")
    public Mono<PageResult<SysUserDTO>> pageUserList(@RequestBody @Validated SysUserPageReq req) {
        log.info("pageUserList req={}", JSONUtil.toJsonStr(req));
        SysUserPageParam param = sysUserConverter.convertSysUserPageParam(req);
        PageResult<SysUserDTO> page = sysUserService.pageUserList(param);
        return Mono.just(page);
    }

    /**
     * 用户详情
     */
    @PostMapping("/getSysUserDetail")
    public Mono<Result<SysUserDTO>> getSysUserDetail(@RequestBody @Validated IdReq req) {
        log.info("getSysUserDetail req={}", JSONUtil.toJsonStr(req));
        SysUserDTO userDTO = sysUserService.getSysUserDetail(req.getId());
        return Mono.just(Result.ok(userDTO));
    }

    /**
     * 新增用户
     */
    @OptLogFlag
    @PasswordValid
    @PostMapping("/addSysUser")
    public Mono<Result<Void>> addSysUser(@RequestBody @Validated SysUserAddReq req) {
        log.info("addSysUser req={}", JSONUtil.toJsonStr(req));
        SysUserAddParam addParam = sysUserConverter.convertSysUserAddParam(req);
        sysUserService.addSysUser(addParam);
        return Mono.just(Result.ok());
    }

    /**
     * 修改用户
     */
    @OptLogFlag
    @PostMapping("/updateSysUser")
    public Mono<Result<Void>> updateSysUser(@RequestBody @Validated SysUserUpdateReq req) {
        log.info("updateSysUser req={}", JSONUtil.toJsonStr(req));
        SysUserUpdateParam param = sysUserConverter.convertSysUserUpdateParam(req);
        sysUserService.updateSysUser(param);
        return Mono.just(Result.ok());
    }

    /**
     * 锁定用户
     */
    @OptLogFlag
    @PostMapping("/lockSysUser")
    public Mono<Result<Void>> lockSysUser(@RequestBody @Validated IdReq req) {
        log.info("lockSysUser req={}", JSONUtil.toJsonStr(req));
        sysUserService.lockSysUser(req.getId());
        return Mono.just(Result.ok());
    }

    /**
     * 解锁用户
     */
    @OptLogFlag
    @PostMapping("/unlockSysUser")
    public Mono<Result<Boolean>> unlockSysUser(@RequestBody @Validated ServerWebExchange exchange, IdReq req) {
        log.info("unlockSysUser req={}", JSONUtil.toJsonStr(req));
        sysUserService.unlockSysUser(req.getId(), exchange);
        return Mono.just(Result.ok());
    }

    /**
     * 验证谷歌验证码
     */
    @PostMapping("/verifyLoginAuthenticator")
    public Mono<Result<Boolean>> verifyAuthCode(@RequestBody @Validated GoogleCodeVerifyReq req, ServerWebExchange exchange) {
        log.info("verifyLoginAuthenticator req={}", JSONUtil.toJsonStr(req));
        AuthCodeVerifyParam param = sysUserConverter.convertAuthCodeVerifyParam(req);
        return Mono.just(Result.ok(sysUserService.verifyAuthCode(param, exchange)));
    }

    /**
     * 显示谷歌验证码
     */
    @PostMapping("/showAuthenticator")
    public Mono<Result<AuthShowDTO>> showAuthenticator() {
        AuthShowDTO showDTO = sysUserService.showAuthenticator();
        return Mono.just(Result.ok(showDTO));
    }

    /**
     * 显示当前谷歌验证码
     */
    @PostMapping("/showCurrentAuthenticator")
    public Mono<Result<String>> showCurrentAuthenticator(ServerWebExchange exchange) {
        return Mono.just(Result.ok(sysUserService.showCurrentAuth(exchange)));
    }

    /**
     * 解绑谷歌
     */
    @PostMapping("/unbindAuthenticator")
    public Mono<Result<Boolean>> unbindAuthenticator(@RequestBody @Validated GoogleCodeUnBindReq req, ServerWebExchange exchange) {
        log.info("unbindAuthenticator req={}", JSONUtil.toJsonStr(req));
        return Mono.just(Result.ok(sysUserService.unbindAuthenticator(req.getUserId(), req.getAuthCode(), exchange)));
    }
}
