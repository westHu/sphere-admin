package com.sphere.pay.config.valid;

import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.sphere.pay.SysRequestManager;
import com.sphere.pay.config.GoogleConfig;
import com.sphere.pay.db.entity.SysUser;
import com.sphere.pay.dto.token.TokenUserDTO;
import com.sphere.pay.enums.ProfileEnum;
import com.sphere.pay.exception.AdminException;
import com.sphere.pay.service.sys.SysUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class GoogleAuthenticatorValidAspect {

    @Resource
    SysUserService sysUserService;

    @Value("${spring.profiles.active:DEV}")
    String profile;

    @Before(value = "@annotation(com.sphere.pay.config.valid.GoogleAuthenticatorValid) && args(req, ..)")
    public void googleAuthenticatorValidBefore(JoinPoint joinPoint, Object req) {
        log.info("googleAuthenticatorValidBefore profile={} req={}", profile, JSONUtil.toJsonStr(req));

        if (!profile.equalsIgnoreCase(ProfileEnum.PROD.name())) {
            return;
        }

        //获取当前密码
        JSONObject jsonObject = JSONUtil.parseObj(req);
        String googleCode = jsonObject.getStr("googleCode");
        Assert.notBlank(googleCode, () -> new AdminException("请输入谷歌验证码"));

        //获取用户信息
        TokenUserDTO currentUser = SysRequestManager.getCurrentUser();
        SysUser sysUser = sysUserService.getById(currentUser.getId());
        Assert.notNull(sysUser, () -> new AdminException("谷歌验证码不正确"));

        //验证验证码
        Boolean match = GoogleConfig.verifyCode(googleCode, sysUser.getSecret());
        Assert.isTrue(match, () -> new AdminException("谷歌验证码不正确"));
    }


}
