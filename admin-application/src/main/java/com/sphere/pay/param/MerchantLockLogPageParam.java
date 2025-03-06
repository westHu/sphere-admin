package com.sphere.pay.param;

import lombok.Data;

@Data
public class MerchantLockLogPageParam extends PageParam {

    /**
     * 锁住登录名
     */
    private String lockName;

    /**
     * 锁住时间
     */
    private String startLockTime;

    /**
     * 锁住时间
     */
    private String endLockTime;

}
