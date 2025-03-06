package com.sphere.pay.convert;

import com.sphere.pay.controller.request.SysResourceAddReq;
import com.sphere.pay.controller.request.SysResourceTreeReq;
import com.sphere.pay.controller.request.SysResourceUpdateReq;
import com.sphere.pay.param.SysResourceAddParam;
import com.sphere.pay.param.SysResourceTreeParam;
import com.sphere.pay.param.SysResourceUpdateParam;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface ResourceConverter {

    SysResourceTreeParam convertSysResourceTreeParam(SysResourceTreeReq req);

    SysResourceAddParam convertSysResourceAddParam(SysResourceAddReq req);

    SysResourceUpdateParam convertSysResourceUpdateParam(SysResourceUpdateReq req);
}
