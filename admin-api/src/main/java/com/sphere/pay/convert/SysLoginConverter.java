package com.sphere.pay.convert;


import com.sphere.pay.controller.request.LoginChangePwdReq;
import com.sphere.pay.controller.request.LoginLogReq;
import com.sphere.pay.controller.request.SysLoginReq;
import com.sphere.pay.param.LoginLogParam;
import com.sphere.pay.param.LoginResetPwdParam;
import com.sphere.pay.param.SysLoginParam;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface SysLoginConverter {

    SysLoginParam convertSysLoginParam(SysLoginReq req);

    LoginResetPwdParam convertLoginResetPwdParam(LoginChangePwdReq req);

    LoginLogParam convertLoginLogParam(LoginLogReq req);

}
