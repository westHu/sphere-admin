package com.sphere.pay.param;

import lombok.Data;

import java.util.List;

/**
 * @author west
 */
@Data
public class SysRoleAddParam {

    /**
     * 资源列表
     */
    List<Long> resourceIdList;

    /**
     * 角色名称
     */
    private String roleName;

}
