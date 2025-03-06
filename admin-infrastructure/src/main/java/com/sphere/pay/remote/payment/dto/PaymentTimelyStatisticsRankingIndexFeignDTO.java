package com.sphere.pay.remote.payment.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentTimelyStatisticsRankingIndexFeignDTO {

    /**
     * 支付方式
     */
    private String paymentMethod;

    /**
     * 成功率
     */
    private BigDecimal successRate;
}
