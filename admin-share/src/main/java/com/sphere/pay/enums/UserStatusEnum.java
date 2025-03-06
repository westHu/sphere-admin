package com.sphere.pay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * 状态
 */
@Getter
@AllArgsConstructor
public enum UserStatusEnum {

    CANCEL(-1, "注销"),
    NORMAL(1, "正常"),
    LOCKED(2, "锁定"),
    FROZEN(3, "冻结"),
    DORMANT(4, "休眠");

    private final Integer code;
    private final String name;


    public static UserStatusEnum codeToEnum(Integer code) {
        if (Objects.isNull(code)) {
            return NORMAL;
        }
        return Arrays.stream(UserStatusEnum.values())
                .filter(e -> e.getCode().equals(code))
                .findAny().orElse(NORMAL);
    }

}
