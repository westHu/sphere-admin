package com.sphere.pay.dto.dashboard;

import lombok.Data;

@Data
public class DashboardDTO {

    /**
     * 工作台
     */
    private DashboardWorkbenchesDTO workbenches;

    /**
     * 概述数据
     */
    private DashboardGeneralDTO general;

    /**
     * 交易数据
     */
    private DashboardTradeDTO trade;

    /**
     * 趋势图
     */
    private DashboardTradeTrendDTO trend;

    /**
     * 清结算
     */
    private DashboardSettleDTO settle;

    /**
     * 通道支付方式数据
     */
    private DashboardPaymentDTO payment;

    /**
     * 平台数据
     */
    private DashboardPlatformDTO platform;

    /**
     * 渠道数据
     */
    private DashboardChannelDTO channel;

}
