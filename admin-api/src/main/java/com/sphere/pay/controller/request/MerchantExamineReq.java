package com.sphere.pay.controller.request;

import lombok.Data;

@Data
public class MerchantExamineReq {

    /**
     * 进件ID
     */
    private Long id;

    /**
     * 进件状态
     */
    private Boolean examineStatus;

    /**
     * 进件说明
     */
    private String examineComment;

}
