package com.sphere.pay.controller.request;

import lombok.Data;

/**
 * @author west
 */
@Data
public class SysRolePageReq extends PageReq {

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 角色
     */
    private String roleName;

    /**
     * 账号状态
     */
    private Boolean status;

}
