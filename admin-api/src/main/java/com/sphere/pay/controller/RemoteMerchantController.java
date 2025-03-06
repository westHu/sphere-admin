package com.sphere.pay.controller;

import cn.hutool.json.JSONUtil;
import com.sphere.pay.config.opt.OptLogFlag;
import com.sphere.pay.config.valid.GoogleAuthenticatorValid;
import com.sphere.pay.remote.merchant.MerchantApiHandler;
import com.sphere.pay.remote.merchant.MerchantApiService;
import com.sphere.pay.remote.merchant.dto.MerchantApiSettingUpdateExchangeDTO;
import com.sphere.pay.remote.merchant.dto.MerchantBaseExchangeDTO;
import com.sphere.pay.remote.merchant.dto.MerchantChannelConfigCombExchangeDTO;
import com.sphere.pay.remote.merchant.dto.MerchantChannelOptExchangeDTO;
import com.sphere.pay.remote.merchant.dto.MerchantDropExchangeDTO;
import com.sphere.pay.remote.merchant.dto.MerchantWithdrawConfigExchangeDTO;
import com.sphere.pay.remote.merchant.dto.SandboxMerchantApiSettingExchangeDTO;
import com.sphere.pay.remote.merchant.param.MerchantApiSettingUpdateExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantChannelConfigAddExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantChannelConfigSyncExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantChannelConfigUpdateExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantChannelOpenOrCloseExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantChannelSwitchExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantIdExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantLoginAuthenticatorUnsetExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantPageExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantPasswordResetExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantUpdateStatusExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantWithdrawConfigUpdateExchangeParam;
import com.sphere.pay.result.PageResult;
import com.sphere.pay.result.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 商户API
 *
 * @author dh
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class RemoteMerchantController {

    @Resource
    MerchantApiService merchantApiService;
    @Resource
    MerchantApiHandler merchantApiHandler;

    /**
     * 商户下拉框
     */
    @PostMapping("/dropMerchantList")
    public Mono<Result<List<MerchantDropExchangeDTO>>> dropMerchantList() {
        //return merchantApiService.dropMerchantList();
        return Mono.empty();
    }

    /**
     * 分页查询商户列表
     */
    @PostMapping("/pageMerchantList")
    public Mono<PageResult<MerchantBaseExchangeDTO>> pageMerchantList(@RequestBody @Validated MerchantPageExchangeParam req) {
        log.info("pageMerchantList req={}", JSONUtil.toJsonStr(req));
        return merchantApiService.pageBaseMerchantList(req);
    }

    /**
     * 查询商户配置渠道
     */
    @PostMapping("/getMerchantChannelConfigList")
    public Mono<Result<MerchantChannelConfigCombExchangeDTO>> getMerchantChannelConfigList(@RequestBody @Validated MerchantIdExchangeParam param) {
        log.info("getMerchantChannelConfigList param={}", JSONUtil.toJsonStr(param));
        return merchantApiService.getMerchantChannelConfigList(param);
    }

    /**
     * 新增商户配置渠道
     */
    @OptLogFlag
    @PostMapping("/addMerchantChannelConfig")
    public Mono<Result<Void>> addMerchantChannel(@RequestBody @Validated MerchantChannelConfigAddExchangeParam req) {
        log.info("addMerchantChannel req={}", JSONUtil.toJsonStr(req));
        return merchantApiService.addMerchantChannelConfig(req);
    }

    /**
     * 修改商户配置渠道状态
     */
    @OptLogFlag
    @PostMapping("/updateMerchantChannelStatus")
    public Mono<Result<Boolean>> updateMerchantChannelStatus(@RequestBody @Validated MerchantChannelConfigUpdateExchangeParam param) {
        log.info("updateMerchantChannelStatus req={}", JSONUtil.toJsonStr(param));
        return merchantApiService.updateMerchantChannelStatus(param);
    }

    /**
     * 修改商户配置渠道优先级
     */
    @OptLogFlag
    @PostMapping("/updateMerchantChannelPriority")
    public Mono<Result<Boolean>> updateMerchantChannelPriority(@RequestBody @Validated MerchantChannelConfigUpdateExchangeParam param) {
        log.info("updateMerchantChannelPriority req={}", JSONUtil.toJsonStr(param));
        return merchantApiService.updateMerchantChannelPriority(param);
    }

    /**
     * 修改商户配置渠道费率
     */
    @OptLogFlag
    @PostMapping("/updateMerchantChannelFee")
    public Mono<Result<Boolean>> updateMerchantChannelFee(@RequestBody @Validated MerchantChannelConfigUpdateExchangeParam param) {
        log.info("updateMerchantChannelFee req={}", JSONUtil.toJsonStr(param));
        return merchantApiService.updateMerchantChannelFee(param);
    }

    /**
     * 重置商户密码 记录操作日志
     */
    @OptLogFlag
    @PostMapping("/resetPassword")
    public Mono<Result<Boolean>> resetPassword(@RequestBody @Validated MerchantPasswordResetExchangeParam param) {
        log.info("resetPassword param={}", JSONUtil.toJsonStr(param));
        return merchantApiService.resetPassword(param);
    }

    /**
     * 查询商户Api设置
     */
    @OptLogFlag
    @PostMapping("/getMerchantApiSetting")
    public Mono<Result<MerchantApiSettingUpdateExchangeDTO>> getMerchantApiSetting(@RequestBody @Validated MerchantIdExchangeParam param) {
        log.info("getMerchantApiSetting param={}", JSONUtil.toJsonStr(param));
        return merchantApiHandler.getMerchantApiSetting(param);
    }

    /**
     * 更新商户Api设置
     */
    @OptLogFlag
    @PostMapping("/updateMerchantApiSetting")
    public Mono<Result<Boolean>> updateMerchantApiSetting(@RequestBody @Validated MerchantApiSettingUpdateExchangeParam param) {
        log.info("updateMerchantApiSetting param={}", JSONUtil.toJsonStr(param));
        return merchantApiHandler.updateMerchantConfig(param);
    }

    /**
     * 沙箱 查询商户Api设置
     */
    @PostMapping("/getSandboxMerchantApiSetting")
    public Mono<Result<SandboxMerchantApiSettingExchangeDTO>> getSandboxMerchantApiSetting(@RequestBody @Validated MerchantIdExchangeParam param) {
        log.info("getSandboxMerchantApiSetting param={}", JSONUtil.toJsonStr(param));
        return merchantApiHandler.getSandboxMerchantApiSetting(param);
    }

    /**
     * 沙箱 更新商户Api设置
     */
    @OptLogFlag
    @PostMapping("/updateSandboxMerchantConfig")
    public Mono<Result<Boolean>> updateSandboxMerchantConfig(@RequestBody @Validated MerchantApiSettingUpdateExchangeParam param) {
        log.info("updateSandboxMerchantConfig param={}", JSONUtil.toJsonStr(param));
        return merchantApiHandler.updateSandboxMerchantConfig(param);
    }

    /**
     * 解除绑定某商户的谷歌验证码
     */
    @OptLogFlag
    @PostMapping("/unsetLoginAuthenticator")
    public Mono<Result<Boolean>> unsetLoginAuthenticator(@RequestBody @Validated MerchantLoginAuthenticatorUnsetExchangeParam param) {
        log.info("unsetLoginAuthenticator param={}", JSONUtil.toJsonStr(param));
        return merchantApiService.unsetLoginAuthenticator(param);
    }


    /**
     * 同步商户配置
     */
    @OptLogFlag
    @GoogleAuthenticatorValid
    @PostMapping("/syncMerchantChannelConfig")
    public Mono<Result<Boolean>> syncMerchantChannelConfig(@RequestBody @Validated MerchantChannelConfigSyncExchangeParam param) {
        log.info("syncMerchantChannelConfig param={}", JSONUtil.toJsonStr(param));
        return merchantApiService.syncMerchantChannelConfig(param);
    }

    /**
     * 切换通道
     */
    @PostMapping("/switchChannel")
    public Mono<Result<MerchantChannelOptExchangeDTO>> switchChannel(@RequestBody @Validated MerchantChannelSwitchExchangeParam param) {
        log.info("switchChannel param={}", JSONUtil.toJsonStr(param));
        return merchantApiService.switchChannel(param);
    }


    /**
     * 开启/关闭通道
     */
    @PostMapping("/openOrCloseChannel")
    public Mono<Result<MerchantChannelOptExchangeDTO>> openOrCloseChannel(@RequestBody @Validated MerchantChannelOpenOrCloseExchangeParam param) {
        log.info("openOrCloseChannel param={}", JSONUtil.toJsonStr(param));
        return merchantApiService.openOrCloseChannel(param);
    }

    /**
     * 查询商户提现配置-admin
     */
    @PostMapping("/getMerchantWithdrawConfig")
    public Mono<Result<MerchantWithdrawConfigExchangeDTO>> getMerchantWithdrawConfig(@RequestBody @Validated
                                                                                  MerchantIdExchangeParam param) {
        log.info("getMerchantWithdrawConfig param={}", JSONUtil.toJsonStr(param));
        return merchantApiService.getMerchantWithdrawConfig(param);
    }

    /**
     * 更新商户提现配置-admin
     */
    @PostMapping("/updateMerchantWithdrawConfig")
    public Mono<Result<Boolean>> updateMerchantWithdrawConfig(@RequestBody @Validated
                                                              MerchantWithdrawConfigUpdateExchangeParam param) {
        log.info("updateMerchantWithdrawConfig param={}", JSONUtil.toJsonStr(param));
        return merchantApiService.updateMerchantWithdrawConfig(param);
    }

    /**
     * 更新状态, 冻结、注销、正常-admin
     */
    @PostMapping("/updateMerchantStatus")
    public Mono<Result<Boolean>> updateMerchantStatus(@RequestBody @Validated MerchantUpdateStatusExchangeParam param) {
        log.info("updateMerchantStatus param={}", JSONUtil.toJsonStr(param));
        return merchantApiService.updateMerchantStatus(param);
    }
}
