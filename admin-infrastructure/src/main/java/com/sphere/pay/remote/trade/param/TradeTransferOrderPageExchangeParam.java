package com.sphere.pay.remote.trade.param;

import com.sphere.pay.remote.PageExchangeParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TradeTransferOrderPageExchangeParam extends PageExchangeParam {

    /**
     * 交易单号
     */
    private String tradeNo;

    /**
     * 转出账户
     */
    private String transferOutAccount;

    /**
     * 转入账户
     */
    private String transferToAccount;

    /**
     * 交易状态
     */
    private Integer tradeStatus;

    /**
     * 交易开始时间
     */
    private String tradeTimeStart;

    /**
     * 交易结束时间
     */
    private String tradeTimeEnd;

}
