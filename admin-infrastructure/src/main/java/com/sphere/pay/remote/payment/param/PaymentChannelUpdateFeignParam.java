package com.sphere.pay.remote.payment.param;


import lombok.Data;

@Data
public class PaymentChannelUpdateFeignParam {

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
     * API地址
     */
    private String url;

    /**
     * 授权
     */
    private String license;

    /**
     * 是否需要进件
     */
    private Boolean division;

    /**
     * 状态
     */
    private Boolean status;

}
