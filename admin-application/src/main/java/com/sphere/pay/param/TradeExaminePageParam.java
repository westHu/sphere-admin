package com.sphere.pay.param;

import com.sphere.pay.enums.ExamineStatusEnum;
import lombok.Data;

@Data
public class TradeExaminePageParam extends PageParam {

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
    private Integer tradeType;

    /**
     * 审核状态
     *
     * @see ExamineStatusEnum
     */
    private Integer status;

}
