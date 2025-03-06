package com.sphere.pay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MerchantChannelTypeEnum {

    /**
     * 收款, 出款
     */
    TRANSACTION(0), CASH(1), OTHER(99);

    private final Integer code;


}
