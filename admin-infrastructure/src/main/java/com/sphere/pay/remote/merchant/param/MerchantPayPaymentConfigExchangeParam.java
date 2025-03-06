package com.sphere.pay.remote.merchant.param;

import lombok.Data;

@Data
public class MerchantPayPaymentConfigExchangeParam {

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 支付方式
     */
    private String paymentMethod;
}
