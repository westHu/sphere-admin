package com.sphere.pay.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sphere.pay.AdminConstant;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountExamineVO {

    /**
     * ID
     */
    private Long id;

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 渠道名称
     */
    private String channelName;

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
     * 审核消息
     */
    private String examineMessage;

    /**
     * 审核操作员
     */
    private String examineOperator;

    /**
     * 审核时间
     */
    @JsonFormat(pattern = AdminConstant.FORMAT0)
    private LocalDateTime examineTime;

    /**
     * 审核状态
     */
    private Integer examineStatus;

    /**
     * 审核意见
     */
    private String examineComment;

    /**
     * 上游审核状态
     */
    private Integer paymentStatus;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = AdminConstant.FORMAT0)
    private LocalDateTime createTime;


}
