package com.sphere.pay.dto.dashboard;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentProportionDTO {

    /**
     * 支付方式
     */
    private String payment;

    /**
     * 金额
     */
    private BigDecimal amount;
}
