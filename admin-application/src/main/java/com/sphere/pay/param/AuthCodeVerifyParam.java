package com.sphere.pay.param;

import lombok.Data;

@Data
public class AuthCodeVerifyParam {

    /**
     * 验证码
     */
    private String authCode;

    /**
     * 秘钥
     */
    private String secret;

}
