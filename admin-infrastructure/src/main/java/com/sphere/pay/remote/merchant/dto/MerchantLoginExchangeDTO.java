package com.sphere.pay.remote.merchant.dto;

import lombok.Data;

@Data
public class MerchantLoginExchangeDTO {

    /**
     * 商户基本信息
     */
    private MerchantBaseExchangeDTO merchant;

    /**
     * 商户基本配置 1对1
     */
    private MerchantConfigExchangeDTO merchantConfig;

    /**
     * 商户操作员 1对多
     */
    private MerchantOperatorExchangeDTO merchantOperator;

}
