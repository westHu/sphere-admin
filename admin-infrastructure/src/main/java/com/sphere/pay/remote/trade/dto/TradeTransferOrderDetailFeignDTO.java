package com.sphere.pay.remote.trade.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sphere.pay.AdminConstant;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TradeTransferOrderDetailFeignDTO {

    /**
     * 交易单号
     */
    private String tradeNo;

    /**
     * 交易状态
     */
    private String tradeStatus;

    /**
     * 结算状态
     */
    private Integer settleStatus;

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
     * 转入账户
     */
    private String transferToAccount;

    /**
     * 转入商户ID
     */
    private String transferToMerchantId;

    /**
     * 转入商户名称
     */
    private String transferToMerchantName;

    /**
     * 转账目的
     */
    private String purpose;

    /**
     * 币种
     */
    private String currency;

    /**
     * 转账金额
     */
    private BigDecimal amount;

    /**
     * 交易时间
     */
    @JsonFormat(pattern = AdminConstant.FORMAT0)
    private LocalDateTime tradeTime;

    /**
     * 结算完成时间
     */
    @JsonFormat(pattern = AdminConstant.FORMAT0)
    private LocalDateTime finishSettleTime;

}
