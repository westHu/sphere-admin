package com.sphere.pay.remote.settle.param;

import com.sphere.pay.remote.PageExchangeParam;
import lombok.Data;

import java.util.List;

@Data
public class AccountPageExchangeParam extends PageExchangeParam {

    /**
     * 账户类型 0 平台账户;  1 商户账户
     */
    private Integer accountType;

    /**
     * 账户类型
     */
    private List<Integer> accountTypeList;

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 商户名称
     */
    private String merchantName;

    /**
     * 账户号
     */
    private String accountNo;


}
