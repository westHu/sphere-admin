package com.sphere.pay.remote.payment.param;

import com.sphere.pay.remote.PageExchangeParam;
import lombok.Data;

@Data
public class PaymentChannelDivisionPageExchangeParam extends PageExchangeParam {

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
    private String merchantName;

    /**
     * 状态
     */
    private Boolean status;
}
