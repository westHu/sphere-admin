package com.sphere.pay.param;

import lombok.Data;

import java.util.List;

/**
 * @author west
 */
@Data
public class SysUserUpdateParam {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户电话
     */
    private String phone;

    /**
     * 账号状态
     */
    private Integer status;

    /**
     * 地区
     */
    private Integer area;

    /**
     * 当前账户密码
     */
    private String currentPwd;

    /**
     * 角色列表
     */
    private List<Long> roleIdList;

}
