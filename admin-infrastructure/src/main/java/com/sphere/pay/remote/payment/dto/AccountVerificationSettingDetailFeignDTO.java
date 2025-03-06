package com.sphere.pay.remote.payment.dto;

import lombok.Data;

@Data
public class AccountVerificationSettingDetailFeignDTO {


    private Long id;

    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 权重
     */
    private int weight;

    /**
     * 状态
     */
    private boolean status;
}
