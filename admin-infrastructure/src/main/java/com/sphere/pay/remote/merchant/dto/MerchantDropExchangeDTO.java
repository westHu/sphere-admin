package com.sphere.pay.remote.merchant.dto;

import lombok.Data;

@Data
public class MerchantDropExchangeDTO {

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 商户名称
     */
    private String merchantName;

}
