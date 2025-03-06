package com.sphere.pay.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author dh
 */
@Data
public class LoginChangePwdReq {

    /**
     * 旧密码
     */
    @NotBlank(message = "oldPassword is required")
    private String oldPassword;

    /**
     * 新密码
     */
    @NotBlank(message = "newPassword is required")
    private String newPassword;

}
