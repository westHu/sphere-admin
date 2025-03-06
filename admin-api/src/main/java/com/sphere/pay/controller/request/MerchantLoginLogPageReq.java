package com.sphere.pay.controller.request;

import com.sphere.pay.remote.PageExchangeParam;
import lombok.Data;

@Data
public class MerchantLoginLogPageReq extends PageExchangeParam {

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
