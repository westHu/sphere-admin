package com.sphere.pay.remote.merchant.dto;

import lombok.Data;

@Data
public class MerchantBaseExchangeDTO {

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 商户号
     */
    private String merchantCode; //ok

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 商户类型
     */
    private Integer merchantType;

    /**
     * 商户等级
     */
    private Integer merchantLevel;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 地区
     */
    private Integer area;

    /**
     * 扩展
     */
    private String attribute;

}
