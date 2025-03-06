package com.sphere.pay.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author west
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_role_resource")
public class SysRoleResource extends BaseEntity {

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long resourceId;

}


