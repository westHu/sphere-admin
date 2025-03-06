package com.sphere.pay.remote.merchant.param;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MerchantChannelConfigAddExchangeParam {

    /**
     * 商户ID
     */
    @NotBlank(message = "merchantId is required")
    private String merchantId;

    /**
     * 交易类型
     */
    @NotNull(message = "tradeType is required")
    private Integer tradeType;

    /**
     * 支付方式
     */
    @NotBlank(message = "paymentMethod is required")
    private String paymentMethod;

    /**
     * 支付方式名称
     */
    @NotBlank(message = "paymentName is required")
    private String paymentName;

    /**
     * 渠道编码
     */
    @NotBlank(message = "channelCode is required")
    private String channelCode;

    /**
     * 渠道名称
     */
    @NotBlank(message = "channelName is required")
    private String channelName;

    /**
     * 渠道名称
     */
    private String channelDivision;

    /**
     * 手续费
     */
    private BigDecimal singleFee;

    /**
     * 手续费率
     */
    private BigDecimal singleRate;

    /**
     * 结算配置
     */
    private String settleType;

    /**
     * 结算时间
     */
    private String settleTime;

    /**
     * 是否需要进件
     */
    private Boolean division;

    /**
     * 权重
     */
    private Integer weights;


}
