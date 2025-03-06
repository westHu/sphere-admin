package com.sphere.pay.remote.merchant.param;

import lombok.Data;


@Data
public class SettlementFileListExchangeParam {

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 开始结算时间
     */
    private String startSettleDate;

    /**
     * 结束结算时间
     */
    private String endSettleDate;
}
