package com.sphere.pay.remote.merchant.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商户出款支付配置
 */
@Data
public class MerchantCashConfigExchangeDTO {

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 出款人工审核开关
     */
    private Boolean payoutReview;

    /**
     * 扣款方式： 0内扣 1外扣
     */
    private Integer deductionType;

    /**
     * 最大金额值
     */
    private BigDecimal maximumAmount;

    /**
     * 最大限次数
     */
    private Integer maximumTimes;

    /**
     * 扩展信息
     */
    private String attribute;


}
