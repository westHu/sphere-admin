package com.sphere.pay.remote.trade.param;

import lombok.Data;

@Data
public class TradeReviewExchangeParam {

    /**
     * 交易单号
     */
    private String tradeNo;

    /**
     * 审核状态
     */
    private Boolean reviewStatus;

    /**
     * 审核意见
     */
    private String reviewMsg;
}
