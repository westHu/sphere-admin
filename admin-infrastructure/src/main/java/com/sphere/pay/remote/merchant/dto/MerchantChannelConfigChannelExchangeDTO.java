package com.sphere.pay.remote.merchant.dto;

import lombok.Data;

@Data
public class MerchantChannelConfigChannelExchangeDTO {

    /**
     * ID
     */
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
     * 优先级
     */
    private Integer priority;


    /**
     * 状态
     */
    private Boolean status;

}
