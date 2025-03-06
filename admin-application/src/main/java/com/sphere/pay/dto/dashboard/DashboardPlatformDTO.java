package com.sphere.pay.dto.dashboard;

import lombok.Data;

@Data
public class DashboardPlatformDTO {

    /**
     * 商户数量
     */
    private PlatformMerchantCountDTO platformMerchantCount = new PlatformMerchantCountDTO();

    /**
     * 商户资金总数
     */
    private PlatformMerchantFundDTO platformMerchantFund = new PlatformMerchantFundDTO();

    /**
     * 平台资金总数
     */
    private PlatformSelfFundDTO platformSelfFund = new PlatformSelfFundDTO();

}
