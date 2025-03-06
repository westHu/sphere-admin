package com.sphere.pay.remote.merchant.param;

import lombok.Data;

@Data
public class MerchantLoginAuthenticatorExchangeParam {

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 用户名
     */
    private String username;

}
