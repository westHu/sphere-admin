package com.sphere.pay.remote.payment.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author Moore
 * @Date 2023/7/6 18:19
 **/
@Data
public class PaymentChannelBalanceInfoFeignDTO {

    /**
     * 余额
     */
    private String balance;

    /**
     * 记录时间
     */
    private LocalDateTime recordTime;
}
