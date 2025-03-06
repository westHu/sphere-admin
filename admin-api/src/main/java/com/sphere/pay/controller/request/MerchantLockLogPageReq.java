package com.sphere.pay.controller.request;

import com.sphere.pay.remote.PageExchangeParam;
import lombok.Data;

@Data
public class MerchantLockLogPageReq extends PageExchangeParam {

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
