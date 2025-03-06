package com.sphere.pay.utils;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.executable.ExecutableValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 校验工具类
 */
@Slf4j
public class ValidationUtil {

    private static Validator obtainValidator() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        return validatorFactory.getValidator();
    }

    private static ExecutableValidator obtainExecutableValidator() {
        return obtainValidator().forExecutables();
    }

    /**
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String getErrorMsg(T obj) {
        Validator validator = ValidationUtil.obtainValidator();
        Set<ConstraintViolation<T>> result = validator.validate(obj);
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }
        String collect =
                result.stream().map(v -> v.getPropertyPath() + " " + v.getMessage() + ": " + v.getInvalidValue())
                        .collect(Collectors.joining());
        log.warn("validationUtil {}. error msg={}", obj.getClass().getSimpleName(), collect);

        return collect;
    }

    /**
     * 直接返回true/false
     */
    public static <T> boolean validateTrue(T obj) {
        return StringUtils.isBlank(getErrorMsg(obj));
    }


}