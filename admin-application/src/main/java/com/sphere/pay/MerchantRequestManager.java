package com.sphere.pay;


import cn.hutool.core.lang.Assert;
import com.sphere.pay.dto.token.TokenMerchantDTO;
import com.sphere.pay.dto.token.TokenMerchantOperatorDTO;
import com.sphere.pay.exception.AdminException;
import io.netty.util.concurrent.FastThreadLocal;

/**
 * 获取登录商户
 */
public class MerchantRequestManager {

    public static FastThreadLocal<TokenMerchantDTO> THREAD_LOCAL_BASE_MERCHANT = new FastThreadLocal<>();
    public static FastThreadLocal<TokenMerchantOperatorDTO> THREAD_LOCAL_MERCHANT_OPERATOR = new FastThreadLocal<>();

    /**
     * 获取登录商户
     */
    public static TokenMerchantDTO getCurrentMerchant() {
        TokenMerchantDTO merchantDTO = THREAD_LOCAL_BASE_MERCHANT.get();
        Assert.notNull(merchantDTO, () -> new AdminException("商户不存在"));
        return merchantDTO;
    }


    /**
     * 获取登录操作员
     */
    public static TokenMerchantOperatorDTO getCurrentMerchantOperator() {
        TokenMerchantOperatorDTO operatorDTO = THREAD_LOCAL_MERCHANT_OPERATOR.get();
        Assert.notNull(operatorDTO, () -> new AdminException("商户登录者不存在"));
        return operatorDTO;
    }


    /**
     * 获取登录商户
     */
    public static void setCurrentMerchant(TokenMerchantDTO merchantDTO) {
        THREAD_LOCAL_BASE_MERCHANT.set(merchantDTO);
    }


    /**
     * 获取登录操作员
     */
    public static void setCurrentMerchantOperator(TokenMerchantOperatorDTO operatorDTO) {
        THREAD_LOCAL_MERCHANT_OPERATOR.set(operatorDTO);
    }

}
