package com.sphere.pay.remote.payment.param;

import lombok.Data;

@Data
public class PaymentChannelMethodPageFeignParam {

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

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 页条数
     */
    private Integer pageSize = 10;
}
