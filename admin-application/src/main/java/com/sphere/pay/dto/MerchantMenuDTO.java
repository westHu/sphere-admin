package com.sphere.pay.dto;

import lombok.Data;

import java.util.List;

@Data
public class MerchantMenuDTO {


    private Long id;

    /**
     * 资源name
     */
    private String name;

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
    private Integer orderNum;

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
     * 英文名
     */
    private String enName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 子节点
     */
    private List<MerchantMenuDTO> children;
}
