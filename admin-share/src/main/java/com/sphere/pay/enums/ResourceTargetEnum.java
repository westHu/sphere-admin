package com.sphere.pay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dh
 * menuItem页签 menuBlank新窗口
 */
@Getter
@AllArgsConstructor
public enum ResourceTargetEnum {


    MENU_ITEM(1, "页签"),
    MENU_BLANK(2, "新窗口"),
    ;

    private final Integer code;
    private final String name;
}
