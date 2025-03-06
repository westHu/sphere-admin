package com.sphere.pay.remote.trade.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TradeRechargeOrderPageExchangeDTO {

    /**
     * 充值单号
     */
    private String tradeNo;

    /**
     * 充值目的
     */
    private String purpose;

    /**
     * 充值商户ID
     */
    private String merchantId;

    /**
     * 充值商户名称
     */
    private String merchantName;

    /**
     * 充值账户
     */
    private String accountNo;

    /**
     * 币种
     */
    private String currency;

    /**
     * 转账金额
     */
    private BigDecimal amount;

    /**
     * 支付方式
     */
    private String paymentMethod;

    /**
     * 充值账户
     */
    private String bankAccount;

    /**
     * 交易状态
     */
    private Integer tradeStatus;

    /**
     * 交易时间
     */
    private LocalDateTime tradeTime;

    /**
     * 结算完成时间
     */
    private LocalDateTime settleFinishTime;

}
