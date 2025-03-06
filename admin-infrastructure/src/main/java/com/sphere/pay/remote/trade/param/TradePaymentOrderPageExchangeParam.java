package com.sphere.pay.remote.trade.param;

import com.sphere.pay.remote.PageExchangeParam;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TradePaymentOrderPageExchangeParam extends PageExchangeParam {

    /**
     * 交易单号
     */
    private String tradeNo;

    /**
     * 商户订单号
     */
    private String merchantNo;

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 商户姓名
     */
    private String merchantName;

    /**
     * 支付方式
     */
    private String paymentMethod;

    /**
     * 渠道
     */
    private String channelName;

    /**
     * 交易状态
     */
    private Integer tradeStatus;

    /**
     * 支付状态
     */
    private Integer paymentStatus;

    /**
     * 交易开始时间
     */
    @NotBlank(message = "tradeTimeStart is required")
    private String tradeTimeStart;

    /**
     * 交易结束时间
     */
    @NotBlank(message = "tradeTimeEnd is required")
    private String tradeTimeEnd;

}
