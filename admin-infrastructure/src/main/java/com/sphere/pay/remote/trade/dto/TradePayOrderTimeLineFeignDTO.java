package com.sphere.pay.remote.trade.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sphere.pay.AdminConstant;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TradePayOrderTimeLineFeignDTO {

    /**
     * 时间轴时间
     */
    @JsonFormat(pattern = AdminConstant.FORMAT0)
    private LocalDateTime lineTime;

    /**
     * 时间轴消息
     */
    private String lineMessage;

    /**
     * 时间轴操作员
     */
    private String lineOperator;

    /**
     * 状态
     */
    private boolean status;

}
