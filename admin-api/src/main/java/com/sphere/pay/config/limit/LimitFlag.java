package com.sphere.pay.config.limit;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LimitFlag {

    /**
     * 未授权的可通过IP，授权的可通过CODE
     */
    LimitTypeEnum limitType() default LimitTypeEnum.USER_ID;

    /**
     * 速率
     */
    double speed() default 1;

}