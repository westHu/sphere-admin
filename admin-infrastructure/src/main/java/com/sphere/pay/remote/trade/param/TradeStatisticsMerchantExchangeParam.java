package com.sphere.pay.remote.trade.param;

import com.sphere.pay.remote.PageExchangeParam;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TradeStatisticsMerchantExchangeParam extends PageExchangeParam {


    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 交易类型
     */
    private Integer tradeType;

    /**
     * 支付方式
     */
    private String paymentMethod;

    /**
     * 渠道编码
     */
    private String channelCode;

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
