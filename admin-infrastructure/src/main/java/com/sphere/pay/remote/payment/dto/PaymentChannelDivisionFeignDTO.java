package com.sphere.pay.remote.payment.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentChannelDivisionFeignDTO {

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 认证ID
     */
    private String divisionId;

    /**
     * 扩展 认证ID
     */
    private String externalDivisionId;

    /**
     * 认证时间
     */
    private LocalDateTime divisionTime;

    /**
     * 状态
     */
    private boolean status;

    /**
     * 扩展
     */
    private String attribute;

}
