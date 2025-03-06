package com.sphere.pay.config.limit;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;


/**
 * @author West.Hu
 */
@Slf4j
//@Component
//@Aspect
public class LimitAspect {


    @Pointcut("@annotation(com.sphere.pay.config.limit.LimitFlag)")
    public void limitAspect() {
    }


    @Before(value = "limitAspect()")
    public void limitAspectBefore(JoinPoint joinPoint) throws NoSuchMethodException {
        log.info("begin limitAspectBefore");

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        // 在代1理类上可能拿不到注解，需要获取接口或父类的方法
        if (method.getDeclaringClass().isInterface()) {
            method = joinPoint.getTarget().getClass().getDeclaredMethod(joinPoint.getSignature().getName(), method.getParameterTypes());
        }

        LimitFlag annotation = method.getAnnotation(LimitFlag.class);
        LimitTypeEnum limitTypeEnum = annotation.limitType();// 获取注解中的值
        log.info("limitAspectBefore limitTypeEnum: " + limitTypeEnum);

        log.info("end limitAspectBefore");
    }



}