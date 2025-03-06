package com.sphere.pay.remote.merchant.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaymentMethodSortedExchangeDTO {

    /**
     * 支付方式类型
     */
    private String paymentType;

    /**
     * 排序
     */
    private int sort;

    /**
     * 支付方式
     */
    private List<String> paymentMethodList;

}
