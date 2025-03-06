package com.sphere.pay.remote.merchant.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class MerchantConfigUpdateExchangeParam {

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 收款完成回调地址
     */
    private String finishPaymentUrl;

    /**
     * 收款完成回调地址
     */
    private String finishCashUrl;

    /**
     * 收款完成回调地址
     */
    private String finishRefundUrl;

    /**
     * 支付完成跳转地址
     */
    private String finishRedirectUrl;

    /**
     * 商户公钥
     */
    @Length(min = 390, max = 400, message = "publicKey is 2048bit")
    private String publicKey;

    /**
     * 商户ip白名单
     */
    private String ipWhiteList;

}
