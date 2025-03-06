package com.sphere.pay.convert;

import com.sphere.pay.controller.request.MerchantLoginLogPageReq;
import com.sphere.pay.param.MerchantLoginLogPageParam;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface MerchantLoginLogConverter {

    MerchantLoginLogPageParam convertMerchantLoginLogPageParam(MerchantLoginLogPageReq req);
}
