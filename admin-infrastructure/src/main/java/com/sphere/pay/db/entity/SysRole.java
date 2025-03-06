package com.sphere.pay.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author west
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_role")
public class SysRole extends BaseEntity {

    /**
     * 角色ID 中文
     */
    private String roleName;

    /**
     * 角色权限字符串 admin
     */
    private String roleKey;

    /**
     * 显示顺序
     */
    private Integer roleSort;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 备注
     */
    private String remark;

}
