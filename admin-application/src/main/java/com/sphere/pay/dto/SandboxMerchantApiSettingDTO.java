package com.sphere.pay.dto;


import lombok.Data;

import java.util.List;

@Data
public class SandboxMerchantApiSettingDTO {

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 商户ID
     */
    private String merchantCode; //ok

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 商户秘钥
     */
    private String merchantSecret;

    /**
     * 收款IP白名单
     */
    private List<String> payInIpWhiteList;

    /**
     * 出款IP白名单
     */
    private List<String> payOutIpWhiteList;

    /**
     * 查询余额IP白名单
     */
    private List<String> checkBalanceIpWhiteList;

    /**
     * 收款回调通知地址
     */
    private String payInNotifyAddress;

    /**
     * 出款回调通知地址
     */
    private String payOutNotifyAddress;

    /**
     * 商户公钥
     */
    private String merchantPublicKey;

    /**
     * 平台公钥
     */
    private String platformPublicKey;

}
