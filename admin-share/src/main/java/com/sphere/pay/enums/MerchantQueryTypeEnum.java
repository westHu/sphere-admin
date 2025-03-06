package com.sphere.pay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MerchantQueryTypeEnum {

    BASE, BASE_EXT, CONFIG, PAY_CONFIG, CASH_CONFIG, WITHDRAW_CONFIG, CHANNEL_CONFIG, ACCOUNT, OPERATOR;

}
