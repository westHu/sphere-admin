package com.sphere.pay.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum ExceptionCode {

    SUCCESS(0, "Request Success"),

    FAILED(10, "Request Failed"),
    UNAUTHORIZED(12, "Unauthorized"),
    OTHER_ERROR(99, "other error");

    private final Integer code;
    private final String message;


    /**
     * 根据code查询ExceptionCode
     */
    public static ExceptionCode codeToExceptionCode(Integer code) {
        if (Objects.isNull(code)) {
            return OTHER_ERROR;
        }
        return Arrays.stream(ExceptionCode.values())
                .filter(e -> e.getCode().equals(code))
                .findAny().orElse(OTHER_ERROR);
    }


}