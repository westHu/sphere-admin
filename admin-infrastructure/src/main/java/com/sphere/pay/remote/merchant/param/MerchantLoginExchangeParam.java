package com.sphere.pay.remote.merchant.param;


import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class MerchantLoginExchangeParam {

    /**
     * 登录类型
     */
    private String mode;

    /**
     * 用户名/商户号都支持
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * google email
     */
    private String googleEmail;

    /**
     * google ID-token
     */
    private String googleCode;

    /**
     * 来自
     */
    private String from;


    /**
     * 默认 web/app
     */
    public String getFrom() {
        if (StringUtils.isBlank(from)) {
            from = "WEB";
        }

        return from;
    }
}
