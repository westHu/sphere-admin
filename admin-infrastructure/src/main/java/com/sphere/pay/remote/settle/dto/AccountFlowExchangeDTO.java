package com.sphere.pay.remote.settle.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sphere.pay.AdminConstant;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AccountFlowExchangeDTO {

    /**
     * 商户流水号
     */
    private String accountFlowNo;

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 账户号
     */
    private String accountNo;

    /**
     * 账户资金方向 1 收入 -1 支出
     */
    private Integer accountDirection;

    /**
     * 账户资金方向描述
     */
    private String accountDirectionDesc;

    /**
     * 币种
     */
    private String currency;

    /**
     * 变动金额
     */
    private BigDecimal amount;

    /**
     * 关联交易单号
     */
    private String tradeNo;

    /**
     * 流水记录时间
     */
    @JsonFormat(pattern = AdminConstant.FORMAT0)
    private LocalDateTime flowTime;

}
