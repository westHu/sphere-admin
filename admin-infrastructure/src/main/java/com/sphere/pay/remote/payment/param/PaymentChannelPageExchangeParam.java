package com.sphere.pay.remote.payment.param;


import com.sphere.pay.remote.PageExchangeParam;
import lombok.Data;

@Data
public class PaymentChannelPageExchangeParam extends PageExchangeParam {

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 是否需要进件
     */
    private Boolean division;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 是否关联 (渠道 与 支付方式)
     */
    private Boolean related;
}
