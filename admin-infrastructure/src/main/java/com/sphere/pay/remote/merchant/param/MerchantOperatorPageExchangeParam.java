package com.sphere.pay.remote.merchant.param;

import com.sphere.pay.remote.PageExchangeParam;
import lombok.Data;

@Data
public class MerchantOperatorPageExchangeParam extends PageExchangeParam {

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 操作员姓名
     */
    private String username;

    /**
     * 角色
     */
    private Long role;
}
