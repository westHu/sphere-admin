package com.sphere.pay.controller.request;

import lombok.Data;

@Data
public class MerchantMenuTreeReq {

    /**
     * 菜单名称
     */
    private String enName;

    /**
     * 父节点
     */
    private Integer parentId;

    /**
     * 是否可见
     */
    private Boolean visible;

}
