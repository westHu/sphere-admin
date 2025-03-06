package com.sphere.pay.convert;

import com.sphere.pay.controller.request.MerchantMenuAddReq;
import com.sphere.pay.controller.request.MerchantMenuTreeReq;
import com.sphere.pay.controller.request.MerchantMenuUpdateReq;
import com.sphere.pay.param.MerchantMenuAddParam;
import com.sphere.pay.param.MerchantMenuTreeParam;
import com.sphere.pay.param.MerchantMenuUpdateParam;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface MerchantMenuConverter {

    MerchantMenuTreeParam convertMerchantMenuTreeParam(MerchantMenuTreeReq req);

    MerchantMenuAddParam convertMerchantMenuAddParam(MerchantMenuAddReq req);

    MerchantMenuUpdateParam convertMerchantMenuUpdateParam(MerchantMenuUpdateReq req);
}
