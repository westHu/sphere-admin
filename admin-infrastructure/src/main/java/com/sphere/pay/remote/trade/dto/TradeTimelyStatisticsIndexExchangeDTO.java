package com.sphere.pay.remote.trade.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class TradeTimelyStatisticsIndexExchangeDTO {

    /**
     * 收款
     */
    private Integer paymentCount = 0;
    private Integer paymentSuccessCount = 0;
    private BigDecimal paymentAmount = BigDecimal.ZERO;
    private BigDecimal paymentSuccessAmount = BigDecimal.ZERO;
    private BigDecimal paymentSuccessRate = BigDecimal.ZERO;


    /**
     * 收款
     */
    private Integer payoutCount = 0;
    private Integer payoutSuccessCount = 0;
    private BigDecimal payoutAmount = BigDecimal.ZERO;
    private BigDecimal payoutSuccessAmount = BigDecimal.ZERO;
    private BigDecimal payoutSuccessRate = BigDecimal.ZERO;

    /**
     * 以往数据 近7天
     */
    private List<TradeTimelyStatisticsIndexSnapshotFeignDTO> snapshotTradeStatisticsList = new ArrayList<>();

}
