package com.sphere.pay.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GoogleCodeShowReq {

    /**
     * 登录名
     */
    @NotBlank(message = "merchantLogin name is required")
    private String loginName;

}
