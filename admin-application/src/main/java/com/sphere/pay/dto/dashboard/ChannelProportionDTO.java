package com.sphere.pay.dto.dashboard;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ChannelProportionDTO {

    /**
     * 渠道
     */
    private String channel;

    /**
     * 金额
     */
    private BigDecimal amount;
}
