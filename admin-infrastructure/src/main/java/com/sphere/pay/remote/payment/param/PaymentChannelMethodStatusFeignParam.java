package com.sphere.pay.remote.payment.param;

import lombok.Data;

@Data
public class PaymentChannelMethodStatusFeignParam {

    /**
     * ID
     */
    private Long id;

    /**
     * 渠道支付方式状态
     */
    private Boolean status;

}
