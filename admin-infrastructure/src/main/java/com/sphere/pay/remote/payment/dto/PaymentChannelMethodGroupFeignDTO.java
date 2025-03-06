package com.sphere.pay.remote.payment.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaymentChannelMethodGroupFeignDTO {

    /**
     * 渠道支付方式列表
     */
    List<PaymentChannelMethodFeignDTO> paymentChannelMethodList;
    /**
     * payment
     * 支付方式编码
     */
    private String paymentMethod;
    /**
     * detailed name
     * 名称
     */
    private String paymentName;
    /**
     * 交易方向 见枚举
     */
    private Integer paymentDirection;
    /**
     * 0/1 status
     * 状态
     */
    private boolean status;
    /**
     * 数量
     */
    private Integer count;


}
