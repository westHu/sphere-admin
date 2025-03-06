package com.sphere.pay.remote.payment.param;

import lombok.Data;

@Data
public class PaymentChannelStatusFeignParam {

    /**
     * ID
     */
    private Long id;

    /**
     * 支付方式状态
     */
    private Boolean status;

    /**
     * 是否一起更新channelMethod
     */
    private Boolean relationMethod = true;


    /**
     * url
     */
    private String url;

    /**
     * license
     */
    private String license;

}
