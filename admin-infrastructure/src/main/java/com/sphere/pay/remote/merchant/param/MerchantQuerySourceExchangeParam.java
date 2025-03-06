package com.sphere.pay.remote.merchant.param;


import com.sphere.pay.enums.MerchantQuerySourceEnum;
import lombok.Data;

@Data
public class MerchantQuerySourceExchangeParam {

    /**
     * 商户查询来源
     */
    private Integer querySource = MerchantQuerySourceEnum.MERCHANT_ADMIN.getCode();


}
