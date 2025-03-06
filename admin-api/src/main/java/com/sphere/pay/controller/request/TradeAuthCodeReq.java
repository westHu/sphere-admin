package com.sphere.pay.controller.request;

import com.sphere.pay.remote.trade.param.TradeNoExchangeParam;
import lombok.Data;

@Data
public class TradeAuthCodeReq extends TradeNoExchangeParam {

    /**
     * 验证码
     */
    private String authCode;
}
