package com.sphere.pay.dto.dashboard;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DashboardPaymentDTO {

    /**
     * 收款通道金额占比
     */
    private List<PaymentProportionDTO> paymentProportionList = new ArrayList<>();

    /**
     * 收款通道成功金额占比
     */
    private List<PaymentSuccessProportionDTO> paymentSuccessProportionList = new ArrayList<>();

    /**
     * 收款通道成功率排行
     */
    private List<PaymentSuccessRankingDTO> paymentSuccessRankingList = new ArrayList<>();


    /**
     * 出款通道数量
     */
    private List<PaymentProportionDTO> payoutProportionList = new ArrayList<>();

    /**
     * 出款通道成功数量
     */
    private List<PaymentSuccessProportionDTO> payoutSuccessProportionList = new ArrayList<>();

    /**
     * 出款通道成功率排行
     */
    private List<PaymentSuccessRankingDTO> payoutSuccessRankingList = new ArrayList<>();


}
