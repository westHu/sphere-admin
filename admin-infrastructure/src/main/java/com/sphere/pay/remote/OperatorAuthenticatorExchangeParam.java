package com.sphere.pay.remote;

import lombok.Data;

@Data
public class OperatorAuthenticatorExchangeParam {

    /**
     * 操作员
     */
    private String operator;

    /**
     * 验证码
     */
    private String authCode;

}
