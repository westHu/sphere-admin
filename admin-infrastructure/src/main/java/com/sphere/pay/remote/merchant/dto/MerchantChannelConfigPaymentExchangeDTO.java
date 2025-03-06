package com.sphere.pay.remote.merchant.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MerchantChannelConfigPaymentExchangeDTO {


    /**
     * 支付方式展示字段
     */
    private String paymentReferred;

    /**
     * 支付方式
     */
    private String paymentMethod;

    /**
     * 支付方式图标
     */
    private String paymentIcon;

    /**
     * 费率
     */
    private BigDecimal singleRate;

    /**
     * 费用
     */
    private BigDecimal singleFee;

    /**
     * 单笔最小
     */
    private BigDecimal amountLimitMin;

    /**
     * 支付方式
     */
    private BigDecimal amountLimitMax;

    /**
     * 结算类型
     */
    private String settleType;

    /**
     * 结算时间
     */
    private String settleTime;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 渠道列表
     */
    private List<MerchantChannelConfigChannelExchangeDTO> channelList;

}
