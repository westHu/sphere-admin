package com.sphere.pay.param;

import lombok.Data;

import java.util.List;

/**
 * @author west
 */
@Data
public class SysRoleUpdateParam {

    /**
     * 资源列表
     */
    List<Long> resourceIdList;
    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色状态
     */
    private Boolean status;

}
