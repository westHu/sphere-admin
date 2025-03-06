package com.sphere.pay.remote.settle.dto;

import lombok.Data;

@Data
public class AccountDropExchangeDTO {

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
