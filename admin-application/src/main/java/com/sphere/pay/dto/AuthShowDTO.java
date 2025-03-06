package com.sphere.pay.dto;

import lombok.Data;

@Data
public class AuthShowDTO {

    /**
     * 秘钥
     */
    private String secret;

    /**
     * 秘钥链接
     */
    private String authUrl;
}
