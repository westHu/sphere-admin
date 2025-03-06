package com.sphere.pay.dto;

import lombok.Data;

/**
 * @author dh
 */
@Data
public class SysRoleDTO {

    /**
     * ID
     */
    private Long id;

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
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    private Integer dataScope;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 资源列表
     */
//    private List<ResourceVO> resourceList;


}
