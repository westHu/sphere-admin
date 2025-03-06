package com.sphere.pay.remote.payment.dto;

import lombok.Data;

@Data
public class PaymentMethodDropFeignDTO {

    /**
     * 支付方式编码
     */
    private String paymentMethod;

    /**
     * 支付方式名称
     */
    private String paymentName;

    /**
     * 支付方式简称
     */
    private String paymentAbbr;

}
