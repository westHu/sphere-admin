package com.sphere.pay.controller.request;

import com.sphere.pay.config.valid.EnumValid;
import com.sphere.pay.enums.MerchantChannelTypeEnum;
import lombok.Data;

@Data
public class MerchantPaymentConfigReq {

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 商户ID
     */
    private String paymentMethod;

    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 类型， 0收款1出款
     */
    @EnumValid(target = MerchantChannelTypeEnum.class, transferMethod = "getCode", message = "type code not support")
    private Integer type;

    /**
     * 权重
     */
    private Integer weight = 0;
}
