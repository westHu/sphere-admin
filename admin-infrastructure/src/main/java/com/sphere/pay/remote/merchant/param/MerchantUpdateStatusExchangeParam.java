package com.sphere.pay.remote.merchant.param;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MerchantUpdateStatusExchangeParam {

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 商户状态
     */
    @NotNull(message = "status is required")
    private Integer status;

}
