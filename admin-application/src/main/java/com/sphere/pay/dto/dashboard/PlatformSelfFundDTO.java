package com.sphere.pay.dto.dashboard;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PlatformSelfFundDTO {

    /**
     * 平台资金总数
     */
    private BigDecimal platformSumAmount = BigDecimal.ZERO;

    /**
     * 平台资金总数 近7日新增
     */
    private BigDecimal platformSumAmountAddIn7Day = BigDecimal.ZERO;

    /**
     * 平台资金总数 昨日新增
     */
    private BigDecimal platformSumAmountInYesterday = BigDecimal.ZERO;
}
