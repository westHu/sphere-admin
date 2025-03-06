package com.sphere.pay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别
 *
 * @author hpj
 */
@Getter
@AllArgsConstructor
public enum SexEnum {

    MALE(1, "男"),
    FEMALE(0, "女"),
    UNKNOWN(-1, "未知");

    private final Integer code;
    private final String name;

}
