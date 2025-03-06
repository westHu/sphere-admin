package com.sphere.pay.message.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TradeCashExamineMessageDTO {

    /**
     * 交易业务号
     */
    private String businessNo;

    /**
     * 交易单号
     */
    private String tradeNo;

    /**
     * 商户订单号
     */
    private String outerNo;

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 商品详情
     */
    private String productDetail;

    /**
     * 交易明细
     */
    private String itemDetailInfo;

    /**
     * 支付方式
     */
    private String paymentMethod;

    /**
     * 出款账户
     */
    private String payoutAccount;

    /**
     * 出款币种
     */
    private String currency;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 付款方信息
     */
    private String payerInfo;

    /**
     * 收款方信息
     */
    private String receiverInfo;

    /**
     * 备注
     */
    private String remark;

}
