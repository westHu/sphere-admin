package com.sphere.pay.remote.merchant.param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

@EqualsAndHashCode(callSuper = true)
@Data
public class MerchantLoginAuthenticatorSetExchangeParam extends MerchantLoginAuthenticatorExchangeParam {

    /**
     * 验证秘钥
     */
    @NotBlank(message = "loginAuth is required")
    private String loginAuth;

    /**
     * 验证码
     */
    @NotBlank(message = "authCode is required")
    @Length(min = 6, max = 6, message = "authCode must be length 6-character")
    private String authCode;

}
