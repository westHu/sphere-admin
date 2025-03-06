package com.sphere.pay.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户类型
 *
 * @author hpj
 */
@Getter
@AllArgsConstructor
public enum UserTypeEnum {


    SYSTEM_USER(1, "系统用户"),
    REGISTER_USER(2, "注册用户"),
    UNKNOWN(-1, "未知");

    private final Integer code;
    private final String name;

}
