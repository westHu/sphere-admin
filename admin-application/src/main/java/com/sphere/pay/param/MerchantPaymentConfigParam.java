package com.sphere.pay.param;

import com.sphere.pay.enums.MerchantChannelTypeEnum;
import lombok.Data;

@Data
public class MerchantPaymentConfigParam {

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
    private MerchantChannelTypeEnum type;

    /**
     * 权重
     */
    private Integer weight;
}
