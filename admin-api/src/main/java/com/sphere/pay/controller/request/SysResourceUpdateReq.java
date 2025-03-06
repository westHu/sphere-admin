package com.sphere.pay.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author west
 */
@Data
public class SysResourceUpdateReq {

    /**
     * 资源ID
     */
    @NotNull(message = "resourceId is required")
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
