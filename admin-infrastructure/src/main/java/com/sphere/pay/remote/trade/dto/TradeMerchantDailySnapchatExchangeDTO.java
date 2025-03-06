package com.sphere.pay.remote.trade.dto;

import lombok.Data;

@Data
public class TradeMerchantDailySnapchatExchangeDTO extends TradeChannelDailySnapchatExchangeDTO {

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 商户名称
     */
    private String merchantName;
}
