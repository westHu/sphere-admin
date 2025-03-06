package com.sphere.pay.remote.merchant.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author dh
 * 商户基本配置信息
 */
@Data
public class MerchantConfigExchangeDTO {

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 商户号
     */
    private String merchantCode; //ok

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 业务作用
     */
    private Integer businessAction;

    /**
     * 商户秘钥
     */
    private String merchantSecret;

    /**
     * 商户收款完成回调地址
     */
    private String finishPaymentUrl;

    /**
     * 商户出款完成回调地址
     */
    private String finishCashUrl;

    /**
     * 商户退款完成回调地址
     */
    private String finishRefundUrl;

    /**
     * 商户支付完成跳转地址
     */
    private String finishRedirectUrl;

    /**
     * 商户公钥
     */
    private String publicKey;

    /**
     * 商户ip白名单
     */
    private String ipWhiteList;

    /**
     * 公钥有效期
     */
    private LocalDateTime expiryDate;

    /**
     * 版本
     */
    private Integer version;

    /**
     * 扩展信息
     */
    private String attribute;

}
