package com.sphere.pay.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 卡类型
 */
@Getter
@AllArgsConstructor
public enum CardTypeEnum {

    DEBIT_CARD(1, "借记卡"), CREDIT_CARD(2, "信用卡");

    private final Integer code;
    private final String name;

}
