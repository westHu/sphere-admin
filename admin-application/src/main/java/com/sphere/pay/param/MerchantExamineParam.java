package com.sphere.pay.param;

import lombok.Data;

@Data
public class MerchantExamineParam {

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
