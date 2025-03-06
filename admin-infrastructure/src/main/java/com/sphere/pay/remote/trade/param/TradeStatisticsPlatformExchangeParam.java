package com.sphere.pay.remote.trade.param;

import com.sphere.pay.remote.PageExchangeParam;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TradeStatisticsPlatformExchangeParam extends PageExchangeParam {

    /**
     * 交易类型
     */
    private Integer tradeType;

    /**
     * 交易开始日期
     */
    @NotBlank(message = "tradeDateStart is required")
    private String tradeDateStart;

    /**
     * 交易结束日期
     */
    @NotBlank(message = "tradeDateEnd is required")
    private String tradeDateEnd;


}
