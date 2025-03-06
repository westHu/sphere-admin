package com.sphere.pay.param;

import lombok.Data;

/**
 * @author west
 */
@Data
public class SysResourceTreeParam {

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
