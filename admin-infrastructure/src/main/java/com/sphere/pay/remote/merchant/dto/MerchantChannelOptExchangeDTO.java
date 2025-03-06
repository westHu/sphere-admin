package com.sphere.pay.remote.merchant.dto;

import lombok.Data;

import java.util.List;

@Data
public class MerchantChannelOptExchangeDTO {

    /**
     * 成功数量
     */
    private Integer successCount;

    /**
     * 失败数量
     */
    private Integer failedCount;

    /**
     * 失败列表
     */
    private List<String> failedMerchantIdList;
}
