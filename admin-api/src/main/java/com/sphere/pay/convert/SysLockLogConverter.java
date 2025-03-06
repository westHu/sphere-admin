package com.sphere.pay.convert;

import com.sphere.pay.controller.request.SysLockLogReq;
import com.sphere.pay.param.SysLockLogParam;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface SysLockLogConverter {
    SysLockLogParam convertSysLockLogParam(SysLockLogReq req);
}
