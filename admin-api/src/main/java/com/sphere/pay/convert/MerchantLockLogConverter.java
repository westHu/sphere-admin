package com.sphere.pay.convert;

import com.sphere.pay.controller.request.MerchantLockLogPageReq;
import com.sphere.pay.controller.request.MerchantUnlockReq;
import com.sphere.pay.param.MerchantLockLogPageParam;
import com.sphere.pay.param.MerchantUnlockParam;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface MerchantLockLogConverter {

    MerchantLockLogPageParam conventMerchantLockLogPageParam(MerchantLockLogPageReq req);

    MerchantUnlockParam convertMerchantUnlockParam(MerchantUnlockReq req);
}
