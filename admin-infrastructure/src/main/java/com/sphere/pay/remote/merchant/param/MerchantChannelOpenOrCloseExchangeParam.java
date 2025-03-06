package com.sphere.pay.remote.merchant.param;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class MerchantChannelOpenOrCloseExchangeParam {

    /**
     * 商户列表
     */
    @NotEmpty(message = "merchantIdList is empty")
    private List<String> merchantIdList;

    /**
     * 开关
     */
    @NotNull(message = "onOff is required")
    private Boolean onOff;

    /**
     * 交易方向 见枚举
     */
    private Integer paymentDirection;

    /**
     * 支付方式
     */
    private String paymentMethod;

    /**
     * 对接的渠道编码
     * unique no
     */
    private String channelCode;

    /**
     * 对接的渠道名称：duiTku、MidTrans、DANA...
     * unique name
     */
    private String channelName;


}
