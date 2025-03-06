package com.sphere.pay.remote.payment.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentTimelyStatisticsProportionIndexFeignDTO {

    /**
     * 支付方式
     */
    private String paymentMethod;

    /**
     * 金额
     */
    private BigDecimal amount;
}
