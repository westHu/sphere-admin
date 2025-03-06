package com.sphere.pay.dto.dashboard;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DashboardSettleDTO {

    /**
     * 收款 商户手续费
     */
    private BigDecimal paymentMerchantFee = BigDecimal.ZERO;

    /**
     * 收款 商户分润
     */
    private BigDecimal paymentMerchantProfit = BigDecimal.ZERO;

    /**
     * 收款 渠道成本
     */
    private BigDecimal paymentChannelCost = BigDecimal.ZERO;

    /**
     * 收款 平台盈利
     */
    private BigDecimal paymentPlatformProfit = BigDecimal.ZERO;


    /**
     * 出款 商户手续费
     */
    private BigDecimal payoutMerchantFee = BigDecimal.ZERO;

    /**
     * 出款 商户分润
     */
    private BigDecimal payoutMerchantProfit = BigDecimal.ZERO;

    /**
     * 出款 渠道成本
     */
    private BigDecimal payoutChannelCost = BigDecimal.ZERO;

    /**
     * 出款 平台盈利
     */
    private BigDecimal payoutPlatformProfit = BigDecimal.ZERO;


}
