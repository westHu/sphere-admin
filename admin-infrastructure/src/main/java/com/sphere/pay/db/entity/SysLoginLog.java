package com.sphere.pay.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_login_log")
public class SysLoginLog extends BaseEntity {

    /**
     * 登录人
     */
    private String loginName;

    /**
     * 类型
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

}
