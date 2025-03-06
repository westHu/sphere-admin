package com.sphere.pay.controller.request;

import com.sphere.pay.enums.ExamineStatusEnum;
import lombok.Data;

@Data
public class AccountExaminePageReq extends PageReq {

    /**
     * 审核ID
     */
    private Long id;

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 添加日期
     */
    private String createStartTime;

    /**
     * 添加日期
     */
    private String createEndTime;

    /**
     * 状态
     *
     * @see ExamineStatusEnum
     */
    private Integer status;

}
