package com.sphere.pay.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * @author west
 */
@Data
public class SysRoleUpdateReq {

    /**
     * 资源列表
     */
    List<Long> resourceIdList;
    /**
     * 角色ID
     */
    @NotNull(message = "roleId is required")
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
