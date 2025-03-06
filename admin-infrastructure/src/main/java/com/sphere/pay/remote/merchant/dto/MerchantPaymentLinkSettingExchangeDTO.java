package com.sphere.pay.remote.merchant.dto;

import lombok.Data;

import java.util.List;

@Data
public class MerchantPaymentLinkSettingExchangeDTO {

    /**
     * logo
     */
    private String logo;

    /**
     * bgColor
     */
    private String bgColor;

    /**
     * 推荐支付方式
     */
    private List<String> recommendedPaymentMethod;

    /**
     * 排序支付方式
     */
    private List<PaymentMethodSortedExchangeDTO> sortedPaymentMethodList;

}
