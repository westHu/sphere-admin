package com.sphere.pay.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sphere.pay.AdminConstant;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TradeExamineVO {

    /**
     * 业务单号
     */
    private String tradeNo;

    /**
     * 订单类型
     */
    private Integer tradeType;

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 审核消息
     */
    private String examineMessage;

    /**
     * 申请时间
     */
    @JsonFormat(pattern = AdminConstant.FORMAT0)
    private LocalDateTime applyTime;

    /**
     * 申请人
     */
    private String applyOperator;

    /**
     * 审核时间
     */
    @JsonFormat(pattern = AdminConstant.FORMAT0)
    private LocalDateTime examineTime;

    /**
     * 审核人
     */
    private String examineOperator;

    /**
     * 审核状态
     */
    private Integer examineStatus;

    /**
     * 审核意见
     */
    private String examineComment;
}
