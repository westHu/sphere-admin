package com.sphere.pay.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SysUserDTO {

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
     * 用户类型
     */
    private Integer userType;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 用户性别
     */
    private Integer sex;

    /**
     * 头像路径
     */
    private String avatar;

    /**
     * 秘钥
     */
    private String secret;

    /**
     * 账号状态
     */
    private Integer status;

    /**
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 密码最后更新时间
     */
    private LocalDateTime loginDate;

    /**
     * 密码最后更新时间
     */
    private LocalDateTime pwdUpdateDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 地区
     */
    private Integer area;

}
