package com.sphere.pay.remote.trade.dto;

import lombok.Data;

import java.util.List;

@Data
public class TradeTransferOrderExchangeDTO {

    /**
     * 订单详情
     */
    private TradeTransferOrderDetailFeignDTO transferOrderDetail;

    /**
     * 订单时间轴
     */
    private List<TradePayOrderTimeLineFeignDTO> timeLine;

}
