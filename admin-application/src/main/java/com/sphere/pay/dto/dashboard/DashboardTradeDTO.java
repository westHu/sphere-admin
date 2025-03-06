package com.sphere.pay.dto.dashboard;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DashboardTradeDTO {

    /**
     * 收款总额
     */
    private BigDecimal paymentAmount = BigDecimal.ZERO;

    /**
     * 收款成功总额
     */
    private BigDecimal paymentSuccessAmount = BigDecimal.ZERO;

    /**
     * 收款订单总数
     */
    private Integer paymentCount = 0;

    /**
     * 收款订单成功总数
     */
    private Integer paymentSuccessCount = 0;

    /**
     * 收款订单成功率
     */
    private BigDecimal paymentSuccessRate = BigDecimal.ZERO;


    /**
     * 出款总额
     */
    private BigDecimal payoutAmount = BigDecimal.ZERO;

    /**
     * 出款成功总额
     */
    private BigDecimal payoutSuccessAmount = BigDecimal.ZERO;

    /**
     * 出款订单总数
     */
    private Integer payoutCount = 0;

    /**
     * 出款订单成功总数
     */
    private Integer payoutSuccessCount = 0;

    /**
     * 出款订单成功率
     */
    private BigDecimal payoutSuccessRate = BigDecimal.ZERO;


}
