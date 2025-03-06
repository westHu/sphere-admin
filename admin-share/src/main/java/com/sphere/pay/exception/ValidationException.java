package com.sphere.pay.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 校验
 */
@EqualsAndHashCode(callSuper = true)
public class ValidationException extends AdminException {

    @Getter
    private Object[] params;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Object[] params) {
        super(message);
        this.params = params;
    }

    public ValidationException(Integer code, String message, Object[] params) {
        super(code, message);
        this.params = params;
    }

    public static ValidationException of(Integer code, Object[] params) {
        return new ValidationException(code, null, params);
    }

}
