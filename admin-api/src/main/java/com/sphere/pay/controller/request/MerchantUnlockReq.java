package com.sphere.pay.controller.request;

import lombok.Data;

@Data
public class MerchantUnlockReq {

    /**
     * ID
     */
    private Long id;

    /**
     * 解锁人
     */
    private String unlockName;

}
