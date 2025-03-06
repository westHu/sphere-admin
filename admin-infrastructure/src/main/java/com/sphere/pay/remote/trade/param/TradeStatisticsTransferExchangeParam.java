package com.sphere.pay.remote.trade.param;

import com.sphere.pay.remote.PageExchangeParam;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class TradeStatisticsTransferExchangeParam extends PageExchangeParam {

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 账户类型
     */
    private List<Integer> accountTypeList;

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
