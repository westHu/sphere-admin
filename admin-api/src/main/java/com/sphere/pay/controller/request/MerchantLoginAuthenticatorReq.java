package com.sphere.pay.controller.request;

import lombok.Data;

@Data
public class MerchantLoginAuthenticatorReq {

    private String merchantId;

    private String username;
}
