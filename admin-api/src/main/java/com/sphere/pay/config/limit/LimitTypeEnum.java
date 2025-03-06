package com.sphere.pay.config.limit;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LimitTypeEnum {

    IP(1), //基于IP限流
    USER_ID(2),//基于用户
    MERCHANT_ID(3); //基于商户

    private final Integer code;


}
