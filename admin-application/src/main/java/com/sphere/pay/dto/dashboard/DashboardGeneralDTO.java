package com.sphere.pay.dto.dashboard;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DashboardGeneralDTO {

    /**
     * 项目名称
     */
    private String projectName = "WHOOSH PAY";

    /**
     * 交易总额
     */
    private BigDecimal tradeAmount = BigDecimal.ZERO;

    /**
     * 交易成功总额
     */
    private BigDecimal tradeSuccessAmount = BigDecimal.ZERO;

    /**
     * 盈利总额
     */
    private BigDecimal platformProfitAmount = BigDecimal.ZERO;

    /**
     * 商户数量
     */
    private Integer merchantCount = 0;

}
