package com.sphere.pay.param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 登录信息
 */
@Data
public class MerchantLoginParam {

    /**
     * 用户名/商户号都支持
     */
    @NotBlank(message = "username is required")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "password is required")
    private String password;

}
