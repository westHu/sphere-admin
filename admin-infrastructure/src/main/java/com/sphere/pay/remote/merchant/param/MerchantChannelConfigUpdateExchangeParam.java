package com.sphere.pay.remote.merchant.param;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MerchantChannelConfigUpdateExchangeParam {

    /**
     * 支付方式
     */
    private String paymentMethod;

    /**
     * 支付方式简称
     */
    private String paymentReferred;

    /**
     * 交易类型
     */
    @NotNull(message = "tradeType is required")
    private Integer tradeType;

    /**
     * ID列表
     */
    @NotEmpty(message = "idList is empty")
    private List<Long> idList;

    /**
     * 优先级
     */
    private Integer priority = 0;

    /**
     * 手续费
     */
    private BigDecimal singleFee;

    /**
     * 手续费率
     */
    private BigDecimal singleRate;

    /**
     * 单笔最小
     */
    private BigDecimal amountLimitMin;

    /**
     * 单笔最大
     */
    private BigDecimal amountLimitMax;

    /**
     * 结算配置
     */
    private String settleType;

    /**
     * 结算时间
     */
    private String settleTime;

    /**
     * 商户渠道配置状态
     */
    private Boolean status;


}
