package com.sphere.pay.remote.merchant.param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PaymentLinkSettingExchangeParam {

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 配置参数
     */
    @NotBlank(message = "paymentLinkSetting is required")
    private String paymentLinkSetting;

}
