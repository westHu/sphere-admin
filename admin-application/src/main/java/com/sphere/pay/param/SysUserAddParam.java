package com.sphere.pay.param;

import lombok.Data;

import java.util.List;

/**
 * @author west
 */
@Data
public class SysUserAddParam {

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
     * 邮箱
     */
    private String email;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 地区
     */
    private Integer area;

    /**
     * 角色列表
     */
    private List<Long> roleIdList;

}
