package com.sphere.pay.remote.merchant.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MerchantChannelConfigCombExchangeDTO {

    /**
     * 收款费率
     */
    private List<MerchantChannelConfigPaymentExchangeDTO> payment = new ArrayList<>();


    /**
     * 出款费率
     */
    private List<MerchantChannelConfigPaymentExchangeDTO> payout = new ArrayList<>();

}
