package com.sphere.pay.service.sys;

import com.sphere.pay.dto.SysLoginDTO;
import com.sphere.pay.dto.SysResourceDTO;
import com.sphere.pay.dto.SysUserInfoDTO;
import com.sphere.pay.dto.token.TokenUserDTO;
import com.sphere.pay.param.LoginResetPwdParam;
import com.sphere.pay.param.SysLoginParam;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

/**
 * @author dh
 */
public interface SysLoginService {

    SysLoginDTO sysLogin(SysLoginParam param, ServerWebExchange exchange);

    SysUserInfoDTO getSysUserInfo(TokenUserDTO currentUser, ServerWebExchange exchange);

    List<SysResourceDTO> getSysMenu(ServerWebExchange exchange);

    void sysLogout(ServerWebExchange exchange);

    void changeSysPwd(LoginResetPwdParam param);

    void resetSysPwd(Long id);


}
