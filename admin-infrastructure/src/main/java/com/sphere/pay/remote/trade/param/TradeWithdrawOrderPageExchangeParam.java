package com.sphere.pay.remote.trade.param;

import com.sphere.pay.remote.PageExchangeParam;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TradeWithdrawOrderPageExchangeParam extends PageExchangeParam {

    /**
     * 交易单号
     */
    private String tradeNo;

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 交易状态
     */
    private Integer tradeStatus;

    /**
     * 交易开始时间
     */
    @NotBlank(message = "tradeTimeStart is required")
    private String tradeTimeStart;

    /**
     * 交易结束时间
     */
    @NotBlank(message = "tradeTimeEnd is required")
    private String tradeTimeEnd;

}
