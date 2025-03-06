package com.sphere.pay.remote.trade.param;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TradeTransferExchangeParam {

    /**
     * 转账目的
     */
    private String purpose;

    /**
     * 转出商户ID
     */
    private String transferOutMerchantId;

    /**
     * 转出商户名称
     */
    private String transferOutMerchantName;

    /**
     * 转出账户
     */
    private String transferOutAccount;

    /**
     * 转入商户ID
     */
    private String transferToMerchantId;

    /**
     * 转入商户名称
     */
    private String transferToMerchantName;

    /**
     * 转入账户
     */
    private String transferToAccount;

    /**
     * 币种
     */
    private String currency;

    /**
     * 转账金额
     */
    private BigDecimal amount;

    /**
     * 申请人
     */
    private String applyOperator;

}
