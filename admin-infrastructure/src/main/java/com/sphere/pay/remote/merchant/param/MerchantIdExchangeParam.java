package com.sphere.pay.remote.merchant.param;

import lombok.Data;

@Data
public class MerchantIdExchangeParam extends MerchantQuerySourceExchangeParam {

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 商户名称
     */
    private String merchantName;
}
