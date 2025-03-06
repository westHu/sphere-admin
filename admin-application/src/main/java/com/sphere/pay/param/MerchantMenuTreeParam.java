package com.sphere.pay.param;

import lombok.Data;

@Data
public class MerchantMenuTreeParam {

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
