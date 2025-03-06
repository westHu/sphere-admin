package com.sphere.pay.controller.request;

import lombok.Data;

@Data
public class MerchantUpdateStatusReq {

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 商户状态
     */
    private Integer status;
}
