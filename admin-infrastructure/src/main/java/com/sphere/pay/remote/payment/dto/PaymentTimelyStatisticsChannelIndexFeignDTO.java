package com.sphere.pay.remote.payment.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentTimelyStatisticsChannelIndexFeignDTO {

    /**
     * 渠道
     */
    private String channelName;

    /**
     * 金额
     */
    private BigDecimal amount;
}
