package com.sphere.pay.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MerchantApiSettingDTO {

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
     * 公钥有效期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiryDate;


}
