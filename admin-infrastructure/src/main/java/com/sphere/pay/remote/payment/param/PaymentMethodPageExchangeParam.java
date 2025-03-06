package com.sphere.pay.remote.payment.param;


import com.sphere.pay.remote.PageExchangeParam;
import lombok.Data;

@Data
public class PaymentMethodPageExchangeParam extends PageExchangeParam {

    /**
     * 支付类型
     */
    private Integer paymentType;

    /**
     * 支付方向
     */
    private Integer paymentDirection;

    /**
     * 支付方式编码
     */
    private String paymentMethod;

    /**
     * 状态
     */
    private Boolean status;

    /**
     * 是否关联 (渠道 与 支付方式)
     */
    private Boolean related;

    /**
     * 关联的渠道编码
     */
    private String channelCode;

}
