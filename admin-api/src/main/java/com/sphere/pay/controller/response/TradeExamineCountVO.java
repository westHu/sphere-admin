package com.sphere.pay.controller.response;

import lombok.Data;

@Data
public class TradeExamineCountVO {

    /**
     * 订单类型
     */
    private Integer tradeType;

    /**
     * 待审核
     */
    private Integer ToExamine;

}
