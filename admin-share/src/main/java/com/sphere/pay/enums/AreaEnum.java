package com.sphere.pay.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Objects;

/**
 * 地区
 *
 * @author hpj
 */
@Getter
@AllArgsConstructor
public enum AreaEnum {

    INDONESIA(10, "印尼", 62),
    UNKNOWN(-1, "未知", -1);

    private final Integer code;
    private final String name;
    private final Integer countryCode;

    /**
     * 根据code查询状态
     *
     * @param code
     * @return
     */
    public static AreaEnum getAreaByCode(Integer code) {
        if (Objects.isNull(code)) {
            return UNKNOWN;
        }
        return Arrays.stream(AreaEnum.values())
                .filter(e -> e.getCode().equals(code))
                .findAny().orElse(UNKNOWN);
    }

    /**
     * 根据name查询状态
     *
     * @param name
     * @return
     */
    public static AreaEnum getAreaByName(String name) {
        if (StringUtils.isBlank(name)) {
            return UNKNOWN;
        }
        return Arrays.stream(AreaEnum.values())
                .filter(e -> e.getName().equals(name))
                .findAny().orElse(UNKNOWN);
    }
}
