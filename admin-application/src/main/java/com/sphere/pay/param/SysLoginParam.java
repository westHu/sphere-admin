package com.sphere.pay.param;

import lombok.Data;

/**
 * @author dh
 */
@Data
public class SysLoginParam {

    /**
     * 登录账号
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;
}
