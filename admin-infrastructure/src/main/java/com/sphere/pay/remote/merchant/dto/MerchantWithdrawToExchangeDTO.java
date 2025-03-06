package com.sphere.pay.remote.merchant.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MerchantWithdrawToExchangeDTO {

    /**
     * 结算\打款到哪
     */
    private String withdrawTo;

    /**
     * 比例
     */
    private BigDecimal ratio;

}
