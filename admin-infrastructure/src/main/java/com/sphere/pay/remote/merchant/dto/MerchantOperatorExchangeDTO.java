package com.sphere.pay.remote.merchant.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MerchantOperatorExchangeDTO {

    /**
     * ID
     */
    private Long id;

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 角色
     */
    private Long role;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 交易密码
     */
    private String tradePassword;

    /**
     * 验证码
     */
    private String loginAuth;

    /**
     * 谷歌授权登录
     */
    private String googleEmail;

    /**
     * 最近密码更新时间
     */
    private LocalDateTime lastPasswordUpdateTime;

    /**
     * 扩展字段
     */
    private String attribute;

}
