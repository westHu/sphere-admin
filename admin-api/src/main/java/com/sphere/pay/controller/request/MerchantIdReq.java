package com.sphere.pay.controller.request;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class MerchantIdReq {

    /**
     * 商户ID
     */
    @Length(max = 32)
    @NotNull(message = "merchantId is required")
    private String merchantId;

}
