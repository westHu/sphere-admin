package com.sphere.pay.remote.payment.dto;

import lombok.Data;

@Data
public class AccountVerificationFeignDTO {

    /**
     * ID
     */
    private Integer id;

    /**
     * 地区
     */
    private Integer area;

    /**
     * 持卡人姓名
     */
    private String holderName;

    /**
     * 支付方式
     */
    private String paymentMethod;

    /**
     * 账户号
     */
    private String accountNo;

    /**
     * 状态
     */
    private boolean status;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 验证次数
     */
    private long verificationTimes;
}
