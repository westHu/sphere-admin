package com.sphere.pay.remote.payment.dto;

import lombok.Data;

@Data
public class PaymentChannelDropFeignDTO {

    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 对接的渠道名称
     */
    private String channelName;

}
