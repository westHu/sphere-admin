package com.sphere.pay.remote.payment.param;

import lombok.Data;

@Data
public class PaymentMethodUpdateFeignParam {

    /**
     * ID
     */
    private Long id;

    /**
     * 支付方式编码
     */
    private String paymentMethod;

    /**
     * 支付方式名称
     */
    private String paymentName;

    /**
     * 支付方式简称
     */
    private String paymentAbbr;

    /**
     * 支付方式类型
     */
    private Integer paymentType;

    /**
     * 支付方式图标
     */
    private String paymentIcon;

    /**
     * 支付方式方向
     */
    private String paymentDirection;

    /**
     * 支付方式状态
     */
    private String status;

}
