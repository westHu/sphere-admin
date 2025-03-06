package com.sphere.pay.remote.settle.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class SettleTimelyStatisticsIndexExchangeDTO {

    /**
     * 收款
     */
    private BigDecimal paymentMerchantFee = BigDecimal.ZERO;
    private BigDecimal paymentMerchantProfit = BigDecimal.ZERO;
    private BigDecimal paymentChannelCost = BigDecimal.ZERO;
    private BigDecimal paymentPlatformProfit = BigDecimal.ZERO;

    /**
     * 出款
     */
    private BigDecimal payoutMerchantFee = BigDecimal.ZERO;
    private BigDecimal payoutMerchantProfit = BigDecimal.ZERO;
    private BigDecimal payoutChannelCost = BigDecimal.ZERO;
    private BigDecimal payoutPlatformProfit = BigDecimal.ZERO;

    /**
     * 商户资金
     */
    private BigDecimal merchantAmount = BigDecimal.ZERO;
    private BigDecimal merchantAmount1Add = BigDecimal.ZERO;
    private BigDecimal merchantAmount7Add = BigDecimal.ZERO;

    /**
     * 平台资金
     */
    private BigDecimal platformAmount = BigDecimal.ZERO;
    private BigDecimal platformAmount1Add = BigDecimal.ZERO;
    private BigDecimal platformAmount7Add = BigDecimal.ZERO;

}
