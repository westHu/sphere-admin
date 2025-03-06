package com.sphere.pay.dto.dashboard;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TradeTrendDTO {

    /**
     * 日期
     */
    private String date;

    /**
     * 总金额
     */
    private BigDecimal amount;

}
