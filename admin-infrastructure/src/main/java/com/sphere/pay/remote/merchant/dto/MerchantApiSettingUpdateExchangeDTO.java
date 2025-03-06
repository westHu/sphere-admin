package com.sphere.pay.remote.merchant.dto;


import lombok.Data;

import java.util.List;

@Data
public class MerchantApiSettingUpdateExchangeDTO {

    /**
     * 商户ID
     */
    private String merchantId;

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


}
