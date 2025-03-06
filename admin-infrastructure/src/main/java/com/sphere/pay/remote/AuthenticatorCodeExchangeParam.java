package com.sphere.pay.remote;

import lombok.Data;

@Data
public class AuthenticatorCodeExchangeParam {

    /**
     * 验证码
     */
    private String authCode;

}
