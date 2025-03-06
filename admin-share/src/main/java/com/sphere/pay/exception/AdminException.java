package com.sphere.pay.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统统一根异常
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AdminException extends RuntimeException {

    private Integer code;
    private String message;

    /**
     * 用给定的异常信息构造新实例
     */
    public AdminException(String message) {
        super(message);
        this.code = 500;
        this.message = message;
    }

    public AdminException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public AdminException(ExceptionCode exceptionCode) {
        this(exceptionCode.getMessage());
        this.code = exceptionCode.getCode();
    }

    public AdminException(ExceptionCode exceptionCode, String message) {
        super(message);
        this.code = exceptionCode.getCode();
        this.message = message;
    }

    public AdminException(Integer code, String message, Throwable cause) {
        this(message, cause);
        this.code = code;
    }

    public AdminException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }


}
