package com.sphere.pay.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MerchantLoginLogDTO {

    private Long id;

    /**
     * merchant merchantLogin name
     */
    private String loginName;

    /**
     * merchantLogin/sysLogout/frozen
     */
    private Integer loginType;

    /**
     * ip
     */
    private String loginIp;

    /**
     * result true/false
     */
    private boolean loginStatus;

    /**
     * when
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
