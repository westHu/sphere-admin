package com.sphere.pay.remote.payment.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaymentTimelyStatisticsIndexFeignDTO {

    /**
     * 收款支付方式金额比例
     */
    private List<PaymentTimelyStatisticsProportionIndexFeignDTO> transactionPaymentProportionList = new ArrayList<>();

    /**
     * 出款支付方式金额比例
     */
    private List<PaymentTimelyStatisticsProportionIndexFeignDTO> disbursementProportionList = new ArrayList<>();


    /**
     * 出款支付方式排行榜
     */
    private List<PaymentTimelyStatisticsRankingIndexFeignDTO> transactionPaymentRankingList = new ArrayList<>();

    /**
     * 出款支付方式排行榜
     */
    private List<PaymentTimelyStatisticsRankingIndexFeignDTO> disbursementPaymentRankingList = new ArrayList<>();


    /**
     * 渠道维度的金额列表
     */
    private List<PaymentTimelyStatisticsChannelIndexFeignDTO> channelProportionList = new ArrayList<>();

}
