package com.sphere.pay.remote.merchant.param;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MerchantWithdrawExchangeParam {

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 提现类型 枚举
     */
    @NotBlank(message = "withdrawTo is required")
    private String withdrawTo;

    /**
     * 账户
     */
    @NotBlank(message = "fromAccountNo is required")
    private String fromAccountNo;

    /**
     * to账户
     */
    private String toAccountNo;

    /**
     * 金额
     */
    @NotNull(message = "amount is required")
    private BigDecimal amount;

    /**
     * 目的
     */
    private String purpose;
}
