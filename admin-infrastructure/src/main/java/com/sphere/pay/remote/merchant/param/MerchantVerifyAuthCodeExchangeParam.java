package com.sphere.pay.remote.merchant.param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class MerchantVerifyAuthCodeExchangeParam {

    /**
     * 用户名
     */
    private String username;

    /**
     * 验证码
     */
    @NotBlank(message = "authCode is required")
    @Length(min = 6, max = 6, message = "authCode must be 6-digit number")
    private String authCode;
}
