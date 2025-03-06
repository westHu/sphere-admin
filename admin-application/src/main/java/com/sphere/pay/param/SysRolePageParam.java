package com.sphere.pay.param;

import lombok.Data;

/**
 * @author west
 */
@Data
public class SysRolePageParam extends PageParam {

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
