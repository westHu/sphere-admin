package com.sphere.pay.controller.request;

import com.sphere.pay.config.valid.EnumValid;
import com.sphere.pay.enums.ExamineStatusEnum;
import lombok.Data;

@Data
public class TradeExamineReq {

    /**
     * 订单号
     */
    private String tradeNo;

    /**
     * 审核状态
     */
    @EnumValid(target = ExamineStatusEnum.class, transferMethod = "getCode", message = "status code not support")
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
