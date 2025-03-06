package com.sphere.pay.dto.dashboard;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PlatformMerchantFundDTO {

    /**
     * 商户资金总数
     */
    private BigDecimal merchantSumAmount = BigDecimal.ZERO;

    /**
     * 商户资金总数 近7日新增
     */
    private BigDecimal merchantSumAmountAddIn7Day = BigDecimal.ZERO;

    /**
     * 商户资金总数 昨日新增
     */
    private BigDecimal merchantSumAmountInYesterday = BigDecimal.ZERO;
}
