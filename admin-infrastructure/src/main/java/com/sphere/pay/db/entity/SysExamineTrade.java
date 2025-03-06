package com.sphere.pay.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author west
 * 订单审核
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "examine_trade")
public class SysExamineTrade extends BaseEntity {

    /**
     * 交易单号
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
    private LocalDateTime applyTime;

    /**
     * 申请人
     */
    private String applyOperator;

    /**
     * 审核时间
     */
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