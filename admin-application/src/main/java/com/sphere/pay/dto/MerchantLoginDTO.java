package com.sphere.pay.dto;

import com.sphere.pay.remote.merchant.dto.MerchantBaseExchangeDTO;
import com.sphere.pay.remote.merchant.dto.MerchantOperatorExchangeDTO;
import lombok.Data;

@Data
public class MerchantLoginDTO {

    private String token;

    private MerchantBaseExchangeDTO merchant;

    private MerchantOperatorExchangeDTO merchantOperator;

    private boolean changePassword;
}
