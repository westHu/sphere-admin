package com.sphere.pay.result;

import com.sphere.pay.exception.ExceptionCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 返回实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Result<T> extends BaseResult {

    private T data;

    public Result() {
    }

    public Result(Integer code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    public static <T> Result<T> ok() {
        Result<T> result = new Result<>();
        result.setCode(ExceptionCode.SUCCESS.getCode());
        result.setMessage(ExceptionCode.SUCCESS.getMessage());
        return result;
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setCode(ExceptionCode.SUCCESS.getCode());
        result.setMessage(ExceptionCode.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    public static Result<Object> error(Integer code, String message, Object data) {
        return new Result<>(code, message, data);
    }
}

