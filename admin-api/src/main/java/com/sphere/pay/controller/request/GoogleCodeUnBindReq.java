package com.sphere.pay.controller.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class GoogleCodeUnBindReq {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 验证码
     */
    @Length(max = 6, message = "authCode max 6")
    private String authCode;

}
