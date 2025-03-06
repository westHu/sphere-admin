package com.sphere.pay.remote.merchant.param;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MerchantWithdrawConfigUpdateExchangeParam {

    /**
     * 商户ID
     */
    @NotBlank(message = "merchantId is required")
    private String merchantId;

    /**
     * 提现到哪
     */
    private String withdrawTo;

    /**
     * 提现周期
     */
    private String withdrawPeriod;

    /**
     * 提现提醒邮件
     */
    private String withdrawPost;

}
