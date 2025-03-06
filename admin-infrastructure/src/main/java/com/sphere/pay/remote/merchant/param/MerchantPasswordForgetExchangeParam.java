package com.sphere.pay.remote.merchant.param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 密码重置
 */
@Data
public class MerchantPasswordForgetExchangeParam {

    /**
     * 用户名
     */
    @NotBlank(message = "username is required")
    @Length(max = 32, message = "username max length 32-character")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "password is required")
    @Length(min = 8, max = 16, message = "password must be 8 to 16 character")
    private String password;


}
