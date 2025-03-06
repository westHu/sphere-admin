package com.sphere.pay.remote.payment.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChannelPaymentMethodGroupFeignDTO {

    /**
     * 渠道支付方式列表
     */
    List<PaymentChannelMethodFeignDTO> paymentChannelMethodList;
    /**
     * 渠道编码
     */
    private String channelCode;
    /**
     * 渠道名称
     */
    private String channelName;
    /**
     * 0/1 status
     * 状态
     */
    private boolean status;
    /**
     * remark
     */
    private Integer count;
}
