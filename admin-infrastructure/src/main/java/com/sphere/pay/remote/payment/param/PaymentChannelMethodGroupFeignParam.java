package com.sphere.pay.remote.payment.param;

import lombok.Data;

@Data
public class PaymentChannelMethodGroupFeignParam {

    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 支付方式编码
     */
    private String paymentMethod;

    /**
     * 支付方向
     */
    private Integer paymentDirection;

}
