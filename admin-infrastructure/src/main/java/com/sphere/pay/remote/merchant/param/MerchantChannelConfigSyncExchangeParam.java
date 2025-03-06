package com.sphere.pay.remote.merchant.param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MerchantChannelConfigSyncExchangeParam {

    /**
     * 商户ID
     */
    @NotBlank(message = "merchantId is required")
    private String merchantId;

    /**
     * 验证码
     */
    @NotBlank(message = "authCode is required")
    private String authCode;

}
