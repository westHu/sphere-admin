package com.sphere.pay.convert;

import com.sphere.pay.controller.request.SysRoleAddReq;
import com.sphere.pay.controller.request.SysRolePageReq;
import com.sphere.pay.controller.request.SysRoleUpdateReq;
import com.sphere.pay.param.SysRoleAddParam;
import com.sphere.pay.param.SysRolePageParam;
import com.sphere.pay.param.SysRoleUpdateParam;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface SysRoleConverter {

    SysRolePageParam convertSysRolePageParam(SysRolePageReq req);

    SysRoleAddParam convertSysRoleAddParam(SysRoleAddReq req);

    SysRoleUpdateParam convertSysRoleUpdateParam(SysRoleUpdateReq req);

}
