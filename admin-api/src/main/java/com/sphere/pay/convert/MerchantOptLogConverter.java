package com.sphere.pay.convert;

import com.sphere.pay.controller.request.MerchantOptLogPageReq;
import com.sphere.pay.param.MerchantOptLogPageParam;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface MerchantOptLogConverter {

    MerchantOptLogPageParam convertMerchantOptLogPageParam(MerchantOptLogPageReq req);

}
