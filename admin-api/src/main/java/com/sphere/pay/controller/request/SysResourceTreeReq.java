package com.sphere.pay.controller.request;

import lombok.Data;

/**
 * @author west
 */
@Data
public class SysResourceTreeReq {

    /**
     * 资源ID
     */
    private Integer resourceId;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 父节点
     */
    private Integer parentId;

    /**
     * 是否可见
     */
    private Boolean visible;

}
