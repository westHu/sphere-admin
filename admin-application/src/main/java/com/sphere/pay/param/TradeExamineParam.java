package com.sphere.pay.param;

import lombok.Data;

@Data
public class TradeExamineParam {

    /**
     * 订单号
     */
    private String tradeNo;

    /**
     * 审核状态
     */
    private Integer examineStatus;

    /**
     * 审核说明
     */
    private String examineComment;

    /**
     * 验证码
     */
    private String authCode;

}
