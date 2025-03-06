package com.sphere.pay.remote.payment.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author Moore
 * @Date 2023/7/6 18:18
 **/
@Data
public class PaymentChannelBalanceFeignDTO {

    /**
     * 渠道余额列表
     */
    public List<PaymentChannelBalanceInfoFeignDTO> balanceInfoList;
    /**
     * 渠道编码
     */
    private String channelCode;
    /**
     * 渠道名称
     */
    private String channelName;
}
