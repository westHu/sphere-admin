package com.sphere.pay.param;

import lombok.Data;

@Data
public class MerchantRoleAddParam {

    /**
     * 角色name
     */
    private String name;

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 角色图标
     */
    private String icon;

    /**
     * 菜单
     */
    private String menu;

    /**
     * 是否展示沙箱
     */
    private boolean sandbox;

    /**
     * 备注
     */
    private String remark;

}
