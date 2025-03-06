package com.sphere.pay.param;

import com.sphere.pay.enums.ExamineStatusEnum;
import lombok.Data;

@Data
public class AccountExaminePageParam extends PageParam {

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
