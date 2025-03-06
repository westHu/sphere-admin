package com.sphere.pay.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "merchant_login_log")
public class MerchantLoginLog extends BaseEntity {

    /**
     * 登录人
     */
    private String loginName;

    /**
     * 类型 登录、登出
     */
    private Integer loginType;

    /**
     * ip
     */
    private String loginIp;

    /**
     * 状态
     */
    private boolean loginStatus;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;

    /**
     * 浏览器信息
     */
    private String loginBrowserInfo;

    /**
     * 操作系统信息
     */
    private String loginOsInfo;

}
