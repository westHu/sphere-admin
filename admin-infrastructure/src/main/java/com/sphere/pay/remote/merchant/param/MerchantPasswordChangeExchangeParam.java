package com.sphere.pay.remote.merchant.param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 密码重置
 */
@Data
public class MerchantPasswordChangeExchangeParam {

    /**
     * 用户名
     */
    private String username;

    /**
     * 旧密码
     */
    @NotBlank(message = "oldPassword is required")
    @Length(min = 8, max = 16, message = "password must be 8 to 16 character")
    private String oldPassword;

    /**
     * 新密码
     */
    @NotBlank(message = "newPassword is required")
    @Length(min = 8, max = 16, message = "password must be 8 to 16 character")
    private String newPassword;

}
