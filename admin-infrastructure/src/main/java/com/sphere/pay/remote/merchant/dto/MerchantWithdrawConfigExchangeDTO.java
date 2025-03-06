package com.sphere.pay.remote.merchant.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商户支付配置
 */
@Data
public class MerchantWithdrawConfigExchangeDTO {

    /**
     * 商户ID
     */
    private String merchantId;

    /**
     * 提现到哪
     */
    private List<MerchantWithdrawToExchangeDTO> withdrawTo;

    /**
     * 提现周期
     */
    private MerchantWithdrawPeriodExchangeDTO withdrawPeriod;

    /**
     * 提现提醒邮件
     */
    private List<String> withdrawPost;

    /**
     * 提现支付方式
     */
    private String withdrawPaymentMethod;

    /**
     * 提现账户
     */
    private String withdrawAccount;

    /**
     * 提现人工审核开关
     */
    private Boolean withdrawReview;

    /**
     * 扣款方式： 0内扣 1外扣
     */
    private Integer deductionType;

    /**
     * 扩展信息
     */
    private String attribute;

    /**
     * 提现费用
     */
    private BigDecimal withdrawFee;

    /**
     * 提现费率
     */
    private BigDecimal withdrawRate;

}
