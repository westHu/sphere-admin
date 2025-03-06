package com.sphere.pay.config;

import com.sphere.pay.exception.AdminException;
import com.sphere.pay.exception.ExceptionCode;
import com.sphere.pay.result.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.Optional;

/**
 * exception controller
 */
@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {


    @ExceptionHandler(AdminException.class)
    public ErrorResult handlerAdminException(AdminException e) {
        log.error("AdminException: ", e);
        return createDataResult(e.getCode(), e.getMessage());
    }

    /**
     * 非法参数异常
     */
    @ExceptionHandler(WebExchangeBindException.class)
    public ErrorResult handlerMethodArgumentNotValidException(WebExchangeBindException e) {
        log.error("WebExchangeBindException: ", e);
        String errorMsg = Optional.ofNullable(e)
                .map(WebExchangeBindException::getBindingResult)
                .map(Errors::getFieldError)
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("Method argument not valid exception");
        return createDataResult(errorMsg);
    }

    /**
     * 非法数据异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult handlerIllegalArgumentException(IllegalArgumentException e) {
        log.error("IllegalArgumentException exception:", e);
        return createDataResult(e.getMessage());
    }

    /**
     * file exception
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ErrorResult handlerMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error("MaxUploadSizeExceededException:", e);
        return createDataResult("File max upload size exceeded");
    }

    /**
     * other
     */
    @ExceptionHandler(Exception.class)
    public ErrorResult handlerException(Exception e) {
        log.error("Exception: ", e);
        return createDataResult(e.getMessage());
    }

    /**
     * create json result
     */
    private ErrorResult createDataResult(String errorMsg) {
        return createDataResult(ExceptionCode.FAILED.getCode(), errorMsg);
    }


    /**
     * create json result
     */
    private ErrorResult createDataResult(Integer code, String errorMsg) {
        ErrorResult result = new ErrorResult();
        result.setCode(code);
        result.setMessage(errorMsg);
        return result;
    }
}
