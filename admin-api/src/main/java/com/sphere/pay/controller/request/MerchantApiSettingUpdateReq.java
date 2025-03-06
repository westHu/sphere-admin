package com.sphere.pay.controller.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Data
public class MerchantApiSettingUpdateReq {

    /**
     * 商户ID
     */
    @NotBlank(message = "merchantId is required")
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
    @URL(message = "Must be URL formatter")
    private String payInNotifyAddress;

    /**
     * 出款回调通知地址
     */
    @URL(message = "Must be URL formatter")
    private String payOutNotifyAddress;

    /**
     * 商户公钥
     */
    @Length(min = 390, max = 400, message = "publicKey is 2048bit")
    private String merchantPublicKey;


}
