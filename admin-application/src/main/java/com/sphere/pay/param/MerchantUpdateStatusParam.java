package com.sphere.pay.param;

import lombok.Data;

@Data
public class MerchantUpdateStatusParam {

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 商户状态
     */
    private Integer status;
}
