package com.sphere.pay.remote.merchant.param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

@EqualsAndHashCode(callSuper = true)
@Data
public class MerchantLoginAuthenticatorUnsetExchangeParam extends MerchantLoginAuthenticatorExchangeParam {

    /**
     * 验证码
     */
    @NotBlank(message = "authCode is required")
    @Length(min = 6, max = 6, message = "authCode must be 6-digit number")
    private String authCode;

}
