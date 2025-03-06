package com.sphere.pay.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sphere.pay.db.entity.SysUser;
import com.sphere.pay.dto.AuthShowDTO;
import com.sphere.pay.dto.SysUserDTO;
import com.sphere.pay.param.AuthCodeVerifyParam;
import com.sphere.pay.param.SysUserAddParam;
import com.sphere.pay.param.SysUserPageParam;
import com.sphere.pay.param.SysUserUpdateParam;
import com.sphere.pay.result.PageResult;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author west
 */
public interface SysUserService extends IService<SysUser> {

    PageResult<SysUserDTO> pageUserList(SysUserPageParam param);

    SysUserDTO getSysUserDetail(Long id);

    void addSysUser(SysUserAddParam param);

    void deleteUser(Long id);

    void updateSysUser(SysUserUpdateParam param);

    boolean lockSysUser(Long id);

    boolean unlockSysUser(Long id, ServerWebExchange exchange);

    boolean verifyAuthCode(AuthCodeVerifyParam param, ServerWebExchange exchange);

    AuthShowDTO showAuthenticator();

    boolean unbindAuthenticator(Long userId, String authCode, ServerWebExchange exchange);

    String showCurrentAuth(ServerWebExchange exchange);


}
