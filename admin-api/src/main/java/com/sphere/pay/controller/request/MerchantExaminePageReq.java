package com.sphere.pay.controller.request;

import com.sphere.pay.enums.ExamineStatusEnum;
import lombok.Data;

@Data
public class MerchantExaminePageReq extends PageReq {

    /**
     * 审核ID
     */
    private Long id;

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 业务类型
     */
    private Integer merchantType;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 注册日期
     */
    private String registerStartTime;

    /**
     * 注册日期
     */
    private String registerEndTime;

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
