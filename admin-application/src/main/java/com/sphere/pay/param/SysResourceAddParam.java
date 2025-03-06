package com.sphere.pay.param;

import lombok.Data;

/**
 * @author west
 */
@Data
public class SysResourceAddParam {

    /**
     * 父节点
     */
    private Long parentId;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 顺序
     */
    private Integer orderNum;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 打开方式
     */
    private String resourceTarget;

    /**
     * 资源类型
     */
    private String resourceType;

    /**
     * 是否可见
     */
    private Boolean visible;

    /**
     * 是否刷新
     */
    private Boolean isRefresh;

    /**
     * 权限
     */
    private String perms;

    /**
     * 图标
     */
    private String icon;

    /**
     * 备注
     */
    private String remark;
}
