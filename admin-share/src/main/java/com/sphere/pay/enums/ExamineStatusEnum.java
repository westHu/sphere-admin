package com.sphere.pay.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExamineStatusEnum {

    TO_EXAMINE(0, "待审核"),
    EXAMINE_PASS(1, "审核通过"),
    EXAMINE_REJECT(-1, "审核驳回"),

    EXAMINING(2, "审核中"),
    ;

    private final Integer code;
    private final String name;
}
