package com.sphere.pay.param;

import lombok.Data;

/**
 * @author dh
 */
@Data
public class LoginResetPwdParam {

    /**
     * username
     */
    private String username;

    /**
     * 旧密码
     */
    private String oldPassword;

    /**
     * 新密码
     */
    private String newPassword;
}
