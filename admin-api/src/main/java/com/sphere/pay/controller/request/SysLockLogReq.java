package com.sphere.pay.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SysLockLogReq extends PageReq {

    /**
     * 登录名称
     */
    private String loginName;

    /**
     * 登录时间
     */
    @NotBlank(message = "loginStartTime is required")
    private String loginStartTime;

    /**
     * 登录时间
     */
    @NotBlank(message = "loginEndTime is required")
    private String loginEndTime;


}
