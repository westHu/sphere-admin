package com.sphere.pay.remote.trade.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TradeStatisticsAgentFeignDTO {

    /**
     * 交易日期 维度
     */
    private LocalDate tradeDate;

    /**
     * 收款订单笔数
     */
    private Integer paymentOrderCount;

    /**
     * 收款订单成功笔数
     */
    private Integer paymentOrderSuccessCount;

    /**
     * 订单金额
     */
    private BigDecimal paymentOrderAmount;

    /**
     * 收款订单成功金额
     */
    private BigDecimal paymentOrderSuccessAmount;

    /**
     * 收款订单成功率
     */
    private BigDecimal paymentOrderSuccessRate;

    /**
     * 出款订单笔数
     */
    private Integer payoutOrderCount;

    /**
     * 出款订单成功笔数
     */
    private Integer payoutOrderSuccessCount;

    /**
     * 出款订单金额
     */
    private BigDecimal payoutOrderAmount;

    /**
     * 出款订单成功金额
     */
    private BigDecimal payoutOrderSuccessAmount;

    /**
     * 出款订单成功率
     */
    private BigDecimal payoutOrderSuccessRate;
}
