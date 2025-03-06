package com.sphere.pay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dh
 * （M目录 C菜单 F按钮）
 */
@Getter
@AllArgsConstructor
public enum ResourceTypeEnum {


    M("目录"), C("菜单"), F("按钮");

    private final String name;
}
