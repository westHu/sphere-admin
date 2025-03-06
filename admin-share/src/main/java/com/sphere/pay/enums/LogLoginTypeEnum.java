package com.sphere.pay.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LogLoginTypeEnum {

    SYS_LOGIN(1, "商户后台登录"),
    MERCHANT_LOGIN(1, "商户后台登录"),
    SYS_LOGOUT(0, "商户后台登出"),
    MERCHANT_LOGOUT(0, "商户后台登出");

    private final Integer code;
    private final String name;
}
