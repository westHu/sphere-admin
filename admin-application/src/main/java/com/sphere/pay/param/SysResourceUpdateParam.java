package com.sphere.pay.param;

import lombok.Data;

/**
 * @author west
 */
@Data
public class SysResourceUpdateParam {

    /**
     * 资源ID
     */
    private Long id;

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
     * 是否可见
     */
    private Boolean visible;


}
