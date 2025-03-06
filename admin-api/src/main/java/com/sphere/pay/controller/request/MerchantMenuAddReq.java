package com.sphere.pay.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author west
 */
@Data
public class MerchantMenuAddReq {

    /**
     * 父节点
     */
    @NotNull(message = "parentId is required")
    private Long parentId;

    /**
     * 菜单名称
     */
    @NotBlank(message = "enName is required")
    private String enName;

    /**
     * 顺序
     */
    @NotNull(message = "orderNum is required")
    private Integer orderNum;

    /**
     * 请求地址
     */
    @NotBlank(message = "url is required")
    private String url;

    /**
     * 打开方式
     */
    private Integer resourceTarget;

    /**
     * 菜单类型
     */
    private String resourceType;

    /**
     * 是否可见
     */
    @NotNull(message = "visible is required")
    private Boolean visible;

    /**
     * 是否刷新
     */
    private Boolean isRefresh = true;

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
