package com.sphere.pay.controller.request;

import com.sphere.pay.remote.trade.param.TradeTransferExchangeParam;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TradeTransferReq extends TradeTransferExchangeParam {

    @NotBlank(message = "currentPassword is required")
    private String currentPassword;
}
