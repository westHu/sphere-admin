package com.sphere.pay.remote.trade.dto;

import lombok.Data;

import java.util.List;

@Data
public class TradePayoutOrderFeignDTO {

    /**
     * 订单详情
     */
    private TradePayoutOrderDetailFeignDTO payoutOrderDetail;

    /**
     * 订单时间轴
     */
    private List<TradePayOrderTimeLineFeignDTO> timeLine;


}
