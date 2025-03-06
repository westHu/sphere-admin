package com.sphere.pay.convert;

import com.sphere.pay.controller.request.SysOptLogReq;
import com.sphere.pay.param.SysOptLogParam;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface SysOptLogConverter {

    SysOptLogParam convertSysOptLogParam(SysOptLogReq req);

}
