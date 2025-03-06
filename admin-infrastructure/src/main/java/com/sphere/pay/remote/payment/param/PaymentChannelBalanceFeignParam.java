package com.sphere.pay.remote.payment.param;

import lombok.Data;

import java.util.List;

@Data
public class PaymentChannelBalanceFeignParam {


    /**
     * 渠道编码
     */
    private List<String> channelCodes;

}