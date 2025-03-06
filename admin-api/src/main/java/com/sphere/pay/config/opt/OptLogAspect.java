package com.sphere.pay.config.opt;


import cn.hutool.json.JSONUtil;
import com.sphere.pay.SysRequestManager;
import com.sphere.pay.db.entity.SysOptLog;
import com.sphere.pay.dto.token.TokenUserDTO;
import com.sphere.pay.service.sys.SysOptLogService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Optional;


/**
 * @author West.Hu
 * 针对认证接口操作
 */
@Slf4j
//@Component
//@Aspect
public class OptLogAspect {

    @Resource
    SysOptLogService sysOptLogService;

    @Around(value = "@annotation(com.sphere.pay.config.opt.OptLogFlag) && args(obj,..)")
    public Object logAroundOpt(ProceedingJoinPoint joinPoint, Object obj) throws Throwable {
        log.info("begin logAroundOpt obj={}", obj);

        String methodName = Optional.of(joinPoint).map(JoinPoint::getSignature)
                .filter(e -> e instanceof MethodSignature)
                .map(e -> (MethodSignature) e)
                .map(MethodSignature::getMethod)
                .map(Method::getName)
                .orElse("Others");

        TokenUserDTO currentUser = SysRequestManager.getCurrentUser();
        String userName = currentUser.getUserName();

        // 继续执行原方法
        Object proceed = joinPoint.proceed();

        SysOptLog optLog = new SysOptLog();
        optLog.setOptName(userName);
        optLog.setMethod(methodName);
        optLog.setParams(JSONUtil.toJsonStr(obj));
        optLog.setIp("0.0.0.0");
        optLog.setResult(JSONUtil.toJsonStr(proceed));
        optLog.setOptTime(LocalDateTime.now());
        optLog.setCreateTime(LocalDateTime.now());
        sysOptLogService.save(optLog);

        return proceed;
    }


}