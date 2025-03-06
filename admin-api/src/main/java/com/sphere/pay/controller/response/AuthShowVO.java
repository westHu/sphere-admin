package com.sphere.pay.controller.response;

import lombok.Data;

@Data
public class AuthShowVO {

    /**
     * 秘钥
     */
    private String secret;

    /**
     * 秘钥
     */
    private String authUrl;
}
