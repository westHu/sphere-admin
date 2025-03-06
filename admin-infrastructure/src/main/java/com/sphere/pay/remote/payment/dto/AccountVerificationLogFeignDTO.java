package com.sphere.pay.remote.payment.dto;

import lombok.Data;

@Data
public class AccountVerificationLogFeignDTO {

    /**
     * ID
     */
    private Integer id;

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 校验时间
     */
    private String verificationTime;
}
