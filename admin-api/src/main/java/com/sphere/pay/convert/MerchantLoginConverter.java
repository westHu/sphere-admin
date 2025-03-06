package com.sphere.pay.convert;

import com.sphere.pay.controller.request.MerchantLoginReq;
import com.sphere.pay.param.MerchantLoginParam;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface MerchantLoginConverter {

    MerchantLoginParam toMerchantLoginParam(MerchantLoginReq req);
}
