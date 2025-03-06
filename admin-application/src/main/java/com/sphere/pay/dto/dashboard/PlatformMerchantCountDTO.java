package com.sphere.pay.dto.dashboard;

import lombok.Data;

@Data
public class PlatformMerchantCountDTO {

    /**
     * 商户数量
     */
    private Integer merchantCount = 0;

    /**
     * 商户数量 近7日新增
     */
    private Integer merchantCountAddIn7Day = 0;

    /**
     * 商户数量 昨日新增
     */
    private Integer merchantCountAddInYesterday = 0;
}
