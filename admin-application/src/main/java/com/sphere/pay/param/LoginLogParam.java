package com.sphere.pay.param;

import lombok.Data;

@Data
public class LoginLogParam extends PageParam {

    /**
     * 登录名称
     */
    private String loginName;

    /**
     * 登录时间
     */
    private String loginStartTime;

    /**
     * 登录时间
     */
    private String loginEndTime;

}
