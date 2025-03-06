package com.sphere.pay.cache;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheFlag {

    /**
     * 存储时长, 单位:秒
     */
    int time() default 60;

    /**
     * 返回结果类型
     */
    Class<?> target() default Object.class;


}