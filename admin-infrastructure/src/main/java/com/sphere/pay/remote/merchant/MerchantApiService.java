package com.sphere.pay.remote.merchant;

import com.sphere.pay.remote.merchant.dto.MerchantBaseExchangeDTO;
import com.sphere.pay.remote.merchant.dto.MerchantCashConfigExchangeDTO;
import com.sphere.pay.remote.merchant.dto.MerchantChannelConfigCombExchangeDTO;
import com.sphere.pay.remote.merchant.dto.MerchantChannelOptExchangeDTO;
import com.sphere.pay.remote.merchant.dto.MerchantConfigExchangeDTO;
import com.sphere.pay.remote.merchant.dto.MerchantLoginExchangeDTO;
import com.sphere.pay.remote.merchant.dto.MerchantOperatorExchangeDTO;
import com.sphere.pay.remote.merchant.dto.MerchantPayPaymentConfigExchangeDTO;
import com.sphere.pay.remote.merchant.dto.MerchantPaymentLinkSettingExchangeDTO;
import com.sphere.pay.remote.merchant.dto.MerchantTimelyStatisticsIndexExchangeDTO;
import com.sphere.pay.remote.merchant.dto.MerchantWithdrawConfigExchangeDTO;
import com.sphere.pay.remote.merchant.dto.SandboxMerchantConfigExchangeDTO;
import com.sphere.pay.remote.merchant.param.MerchantChannelConfigAddExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantChannelConfigSyncExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantChannelConfigUpdateExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantChannelOpenOrCloseExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantChannelSwitchExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantConfigUpdateExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantIdExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantLoginAuthenticatorExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantLoginAuthenticatorSetExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantLoginAuthenticatorUnsetExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantLoginExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantOperatorPageExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantPageExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantPasswordChangeExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantPasswordForgetExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantPasswordResetExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantPayPaymentConfigExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantUpdateStatusExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantVerifyAuthCodeExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantWithdrawConfigUpdateExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantWithdrawExchangeParam;
import com.sphere.pay.remote.merchant.param.PaymentLinkSettingExchangeParam;
import com.sphere.pay.remote.merchant.param.SandboxMerchantConfigUpdateExchangeParam;
import com.sphere.pay.result.PageResult;
import com.sphere.pay.result.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@HttpExchange
public interface MerchantApiService {




    //----- 登录 ------


    /**
     * 商户登录
     */
    @PostExchange("/login")
    Mono<Result<MerchantLoginExchangeDTO>> merchantLogin(@RequestBody MerchantLoginExchangeParam param);

    /**
     * 商户登录-验证谷歌验证码
     */
    @PostExchange("/verifyLoginAuthenticator")
    Mono<Result<Boolean>> verifyLoginAuthenticator(@RequestBody MerchantVerifyAuthCodeExchangeParam req);

    /**
     * 展示谷歌验证器验证码
     */
    @PostExchange("/getLoginAuthenticator")
    Mono<Result<String>> getLoginAuthenticator(@RequestBody MerchantLoginAuthenticatorExchangeParam req);

    /**
     * 绑定谷歌验证器
     */
    @PostExchange("/setLoginAuthenticator")
    Mono<Result<Boolean>> setLoginAuthenticator(@RequestBody MerchantLoginAuthenticatorSetExchangeParam req);

    /**
     * 解绑谷歌验证器
     */
    @PostExchange("/unsetLoginAuthenticator")
    Mono<Result<Boolean>> unsetLoginAuthenticator(@RequestBody MerchantLoginAuthenticatorUnsetExchangeParam req);

    /**
     * 商户忘记密码
     */
    @PostExchange("/forgetPassword")
    Mono<Result<Boolean>> forgetPassword(@RequestBody MerchantPasswordForgetExchangeParam req);

    /**
     * 商户修改密码
     */
    @PostExchange("/changePassword")
    Mono<Result<Boolean>> changePassword(@RequestBody MerchantPasswordChangeExchangeParam req);



    //----- 配置 ------

    /**
     * 分页查询商户基本信息列表
     */
    @PostExchange("/pageBaseMerchantList")
    Mono<PageResult<MerchantBaseExchangeDTO>> pageBaseMerchantList(@RequestBody MerchantPageExchangeParam param);

    /**
     * 分页查询商户操作员
     */
    @PostExchange("/pageMerchantOperatorList")
    Mono<PageResult<MerchantOperatorExchangeDTO>> pageMerchantOperatorList(@RequestBody MerchantOperatorPageExchangeParam param);

    /**
     * 沙箱 查询商户沙箱配置
     */
    @PostExchange("/sandbox/getMerchantConfig")
    Mono<Result<SandboxMerchantConfigExchangeDTO>> getSandboxMerchantConfig(@RequestBody MerchantIdExchangeParam param);
    /**
     * 查询商户配置
     */
    @PostExchange("/getMerchantConfig")
    Mono<Result<MerchantConfigExchangeDTO>> getMerchantConfig(@RequestBody MerchantIdExchangeParam param);

    /**
     * 查询支付链接配置
     */
    @PostExchange("/getPaymentLinkSetting")
    Mono<Result<MerchantPaymentLinkSettingExchangeDTO>> getPaymentLinkSetting(@RequestBody MerchantIdExchangeParam param);


    /**
     * 查询商户收款配置
     */
    @PostExchange("/getMerchantPayPaymentConfigList")
    Mono<Result<List<MerchantPayPaymentConfigExchangeDTO>>> getMerchantPayPaymentConfigList(@RequestBody MerchantPayPaymentConfigExchangeParam req);

    /**
     * 查询商户出款配置
     */
    @PostExchange("/getMerchantCashConfig")
    Mono<Result<MerchantCashConfigExchangeDTO>> getMerchantCashConfig(@RequestBody MerchantIdExchangeParam param);

    /**
     * 查询商户提现配置
     */
    @PostExchange("/getMerchantWithdrawConfig")
    Mono<Result<MerchantWithdrawConfigExchangeDTO>> getMerchantWithdrawConfig(@RequestBody MerchantIdExchangeParam param);

    /**
     * 查询商户渠道配置
     */
    @PostExchange("/getMerchantChannelConfigList")
    Mono<Result<MerchantChannelConfigCombExchangeDTO>> getMerchantChannelConfigList(@RequestBody MerchantIdExchangeParam param);






    //----- 操作


    /**
     * 沙箱 更新商户沙箱配置
     */
    @PostExchange("/sandbox/updateMerchantConfig")
    Mono<Result<Boolean>> updateSandboxMerchantConfig(@RequestBody SandboxMerchantConfigUpdateExchangeParam param);

    /**
     * 更新支付链接配置
     */
    @PostExchange("/updatePaymentLinkSetting")
    Mono<Result<Boolean>> updatePaymentLinkSetting(@RequestBody PaymentLinkSettingExchangeParam param);


    /**
     * 更新商户提现配置
     */
    @PostExchange("/updateMerchantWithdrawConfig")
    Mono<Result<Boolean>> updateMerchantWithdrawConfig(@RequestBody MerchantWithdrawConfigUpdateExchangeParam param);

    /**
     * 更新商户沙箱配置
     */
    @PostExchange("/updateMerchantConfig")
    Mono<Result<Boolean>> updateMerchantConfig(@RequestBody MerchantConfigUpdateExchangeParam param);

    /**
     * 提现 可提现到银行\出款账户
     */
    @PostExchange("/withdraw")
    Mono<Result<Boolean>> withdraw(@RequestBody MerchantWithdrawExchangeParam param);

    /**
     * 更新状态
     */
    @PostExchange("/updateMerchantStatus")
    Mono<Result<Boolean>> updateMerchantStatus(@RequestBody MerchantUpdateStatusExchangeParam param);


    Mono<Result<Void>> addMerchantChannelConfig(MerchantChannelConfigAddExchangeParam req);

    Mono<Result<Boolean>> updateMerchantChannelStatus(MerchantChannelConfigUpdateExchangeParam param);

    Mono<Result<Boolean>> updateMerchantChannelPriority(MerchantChannelConfigUpdateExchangeParam param);

    Mono<Result<Boolean>> updateMerchantChannelFee(MerchantChannelConfigUpdateExchangeParam param);

    Mono<Result<Boolean>> resetPassword(MerchantPasswordResetExchangeParam param);

    Mono<Result<Boolean>> syncMerchantChannelConfig(MerchantChannelConfigSyncExchangeParam param);

    Mono<Result<MerchantChannelOptExchangeDTO>> switchChannel(MerchantChannelSwitchExchangeParam param);

    Mono<Result<MerchantChannelOptExchangeDTO>> openOrCloseChannel(MerchantChannelOpenOrCloseExchangeParam param);

    Mono<Result<MerchantTimelyStatisticsIndexExchangeDTO>> getMerchantTimelyStatistics4Index();


}
