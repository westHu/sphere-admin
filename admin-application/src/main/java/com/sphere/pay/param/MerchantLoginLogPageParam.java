package com.sphere.pay.param;

import lombok.Data;

@Data
public class MerchantLoginLogPageParam extends PageParam {

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 登录时间
     */
    private String startLoginTime;

    /**
     * 登录时间
     */
    private String endLoginTime;

}
