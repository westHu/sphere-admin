package com.sphere.pay.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthCodeReq {

    /**
     * 验证码
     */
    @NotBlank(message = "authCode is required")
    private String authCode;

}
