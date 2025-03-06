package com.sphere.pay.config.login;


import com.sphere.pay.controller.request.SysLoginReq;
import com.sphere.pay.db.entity.SysLoginLog;
import com.sphere.pay.service.sys.SysLoginLogService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


/**
 * @author West.Hu
 */
@Slf4j
@Component
@Aspect
public class LoginLogAspect {

    @Resource
    SysLoginLogService sysLoginLogService;

    @Around(value = "@annotation(com.sphere.pay.config.login.LoginLogFlag) && args(req,..)")
    public Object logAroundLogin(ProceedingJoinPoint joinPoint, SysLoginReq req) throws Throwable {
        log.info("begin loginAspect req={}", req.getUsername());

        // 继续执行原方法
        boolean loginStatus = true;
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            loginStatus = false;
            throw e;
        } finally {
            String username = req.getUsername();
            if (StringUtils.isNotBlank(username)) {
                SysLoginLog loginLog = new SysLoginLog();
                loginLog.setLoginName(username);
                loginLog.setLoginType(1);
                loginLog.setLoginIp("0.0.0.0");
                loginLog.setLoginStatus(loginStatus);
                loginLog.setLoginTime(LocalDateTime.now());
                sysLoginLogService.save(loginLog);
            }
        }
    }

}