package com.sphere.pay.param;

import lombok.Data;

@Data
public class MerchantUnlockParam {

    /**
     * ID
     */
    private Long id;

    /**
     * 解锁人
     */
    private String unlockName;

}
