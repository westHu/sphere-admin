package com.sphere.pay.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author west
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_resource")
public class SysResource extends BaseEntity {

    /**
     * 资源name
     */
    private String resourceName;

    /**
     * 父节点
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 打开方式（menuItem页签 menuBlank新窗口）
     */
    private String target;

    /**
     * 菜单类型
     */
    private String resourceType;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    private Boolean visible;

    /**
     * 是否刷新（0刷新 1不刷新）
     */
    private Boolean isRefresh;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;

}

