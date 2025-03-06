package com.sphere.pay.remote.trade.param;

import com.sphere.pay.remote.OperatorExchangeParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TradeNoExchangeParam extends OperatorExchangeParam {

    /**
     * 交易单号
     */
    private String tradeNo;

}
