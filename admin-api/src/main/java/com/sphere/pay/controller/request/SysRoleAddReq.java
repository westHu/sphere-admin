package com.sphere.pay.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * @author west
 */
@Data
public class SysRoleAddReq {

    /**
     * 资源列表
     */
    @NotEmpty(message = "resourceIdList is empty")
    List<Long> resourceIdList;
    /**
     * 角色名称
     */
    @NotNull(message = "roleName is required")
    @Length(max = 30, message = "roleName length max 30")
    private String roleName;

}
