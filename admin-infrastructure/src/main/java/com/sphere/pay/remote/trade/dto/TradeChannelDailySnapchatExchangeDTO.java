package com.sphere.pay.remote.trade.dto;

import lombok.Data;

@Data
public class TradeChannelDailySnapchatExchangeDTO extends TradePlatformDailySnapchatExchangeDTO {

    /**
     * 支付方式
     */
    private String paymentMethod;

    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 渠道名称
     */
    private String channelName;

}
