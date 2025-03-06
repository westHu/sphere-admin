package com.sphere.pay.convert;

import com.sphere.pay.controller.request.GoogleCodeVerifyReq;
import com.sphere.pay.controller.request.SysUserAddReq;
import com.sphere.pay.controller.request.SysUserPageReq;
import com.sphere.pay.controller.request.SysUserUpdateReq;
import com.sphere.pay.controller.response.AuthShowVO;
import com.sphere.pay.dto.AuthShowDTO;
import com.sphere.pay.param.AuthCodeVerifyParam;
import com.sphere.pay.param.SysUserAddParam;
import com.sphere.pay.param.SysUserPageParam;
import com.sphere.pay.param.SysUserUpdateParam;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface SysUserConverter {

    SysUserPageParam convertSysUserPageParam(SysUserPageReq req);

    SysUserAddParam convertSysUserAddParam(SysUserAddReq req);

    SysUserUpdateParam convertSysUserUpdateParam(SysUserUpdateReq req);

    AuthShowVO convertAuthShowVO(AuthShowDTO showDTO);

    AuthCodeVerifyParam convertAuthCodeVerifyParam(GoogleCodeVerifyReq req);
}
