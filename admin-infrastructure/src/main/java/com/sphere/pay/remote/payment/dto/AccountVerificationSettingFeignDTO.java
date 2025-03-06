package com.sphere.pay.remote.payment.dto;

import lombok.Data;

import java.util.List;

@Data
public class AccountVerificationSettingFeignDTO {

    /**
     * ID
     */
    private Integer id;

    /**
     * 地区
     */
    private Integer area;

    /**
     * 支付类型
     */
    private Integer paymentType;

    /**
     * 渠道数量
     */
    private Integer channelCount;

    /**
     * 状态
     */
    private boolean status;

    /**
     * 明细
     */
    private List<AccountVerificationSettingDetailFeignDTO> detailList;

}
