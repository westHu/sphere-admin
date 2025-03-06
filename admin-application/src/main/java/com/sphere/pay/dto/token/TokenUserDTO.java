package com.sphere.pay.dto.token;

import lombok.Data;

/**
 * @author west
 */
@Data
public class TokenUserDTO {

    /**
     * ID
     */
    private Long id;

    /**
     * 登录账号
     */
    private String loginName;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户类型（系统用户 注册用户）
     */
    private Integer userType;

}
