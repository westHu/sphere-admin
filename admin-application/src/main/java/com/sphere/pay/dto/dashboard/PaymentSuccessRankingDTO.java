package com.sphere.pay.dto.dashboard;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentSuccessRankingDTO {

    /**
     * 通道=支付方式
     */
    private String payment;

    /**
     * 成功率
     */
    private BigDecimal successRate;
}
