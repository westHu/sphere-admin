package com.sphere.pay.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "merchant_menu")
public class MerchantMenu extends BaseEntity {

    /**
     * 资源name
     */
    private String name;

    /**
     * 英文名
     */
    private String enName;

    /**
     * 印尼名
     */
    private String inaName;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 父节点
     */
    private Long parentId;

    /**
     * 菜单类型
     */
    private String type;

    /**
     * 显示顺序
     */
    private Integer orderNum = 1;


    /**
     * 菜单状态（1显示 0隐藏）
     */
    private Integer visible = 1;

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
