package com.sphere.pay.controller.request;

import com.sphere.pay.enums.ExamineStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TradeExaminePageReq extends PageReq {

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 交易单号
     */
    private String tradeNo;

    /**
     * 订单类型
     */
    @NotNull(message = "tradeType is required")
    private Integer tradeType;

    /**
     * 审核状态
     *
     * @see ExamineStatusEnum
     */
    private Integer status;

}
