package com.sphere.pay.remote.merchant;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.sphere.pay.config.SandboxPlatformKeyConfig;
import com.sphere.pay.remote.merchant.dto.MerchantApiSettingUpdateExchangeDTO;
import com.sphere.pay.remote.merchant.dto.MerchantConfigExchangeDTO;
import com.sphere.pay.remote.merchant.dto.SandboxMerchantApiSettingExchangeDTO;
import com.sphere.pay.remote.merchant.dto.SandboxMerchantConfigExchangeDTO;
import com.sphere.pay.remote.merchant.param.MerchantApiSettingUpdateExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantConfigUpdateExchangeParam;
import com.sphere.pay.remote.merchant.param.MerchantIdExchangeParam;
import com.sphere.pay.remote.merchant.param.SandboxMerchantConfigUpdateExchangeParam;
import com.sphere.pay.result.BaseResult;
import com.sphere.pay.result.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static com.sphere.pay.AdminConstant.SANDBOX;

@Slf4j
@Component
public class MerchantApiHandler {

    @Resource
    MerchantApiService merchantApiService;

    @Resource
    SandboxPlatformKeyConfig sandboxPlatformKeyConfig;

    /**
     * 查询商户API设置
     */
    public Mono<Result<MerchantApiSettingUpdateExchangeDTO>> getMerchantApiSetting(MerchantIdExchangeParam dto) {
        MerchantIdExchangeParam feignParam = new MerchantIdExchangeParam();
        feignParam.setMerchantId(dto.getMerchantId());

        return merchantApiService.getMerchantConfig(feignParam).map(result -> {
            MerchantConfigExchangeDTO configFeignDTO = BaseResult.parse(result);

            Optional<JSONObject> jsonObj = Optional.ofNullable(configFeignDTO).map(MerchantConfigExchangeDTO::getIpWhiteList)
                    .map(JSONUtil::parseObj);
            MerchantApiSettingUpdateExchangeDTO settingDTO = new MerchantApiSettingUpdateExchangeDTO();
            jsonObj.map(e -> e.getStr("payment")).map(e -> e.split(";")).map(Arrays::asList).ifPresent(settingDTO::setPayInIpWhiteList);
            jsonObj.map(e -> e.getStr("payout")).map(e -> e.split(";")).map(Arrays::asList).ifPresent(settingDTO::setPayOutIpWhiteList);
            jsonObj.map(e -> e.getStr("balance")).map(e -> e.split(";")).map(Arrays::asList).ifPresent(settingDTO::setCheckBalanceIpWhiteList);

            Optional.ofNullable(configFeignDTO).map(MerchantConfigExchangeDTO::getFinishPaymentUrl)
                    .ifPresent(settingDTO::setPayInNotifyAddress);
            Optional.ofNullable(configFeignDTO).map(MerchantConfigExchangeDTO::getFinishCashUrl)
                    .ifPresent(settingDTO::setPayOutNotifyAddress);

            Optional.ofNullable(configFeignDTO).map(MerchantConfigExchangeDTO::getPublicKey)
                    .ifPresent(settingDTO::setMerchantPublicKey);

            return Result.ok(settingDTO);
        });

    }


    public Mono<Result<Boolean>> updateMerchantConfig(MerchantApiSettingUpdateExchangeParam dto) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.putOpt("payment", String.join(";", dto.getPayInIpWhiteList()));
        jsonObj.putOpt("payout", String.join(";", dto.getPayOutIpWhiteList()));
        jsonObj.putOpt("balance", String.join(";", dto.getCheckBalanceIpWhiteList()));

        MerchantConfigUpdateExchangeParam feignParam = new MerchantConfigUpdateExchangeParam();
        feignParam.setMerchantId(dto.getMerchantId());
        feignParam.setIpWhiteList(JSONUtil.toJsonStr(jsonObj));
        feignParam.setFinishPaymentUrl(dto.getPayInNotifyAddress());
        feignParam.setFinishCashUrl(dto.getPayOutNotifyAddress());
        feignParam.setPublicKey(dto.getMerchantPublicKey());
        return merchantApiService.updateMerchantConfig(feignParam);
    }


    /**
     * 沙箱 查询商户API设置
     */
    public Mono<Result<SandboxMerchantApiSettingExchangeDTO>> getSandboxMerchantApiSetting(MerchantIdExchangeParam dto) {
        MerchantIdExchangeParam feignParam = new MerchantIdExchangeParam();
        feignParam.setMerchantId(SANDBOX + dto.getMerchantId());


        return merchantApiService.getSandboxMerchantConfig(feignParam).map(result -> {
            SandboxMerchantConfigExchangeDTO feignDTO = BaseResult.parse(result);
            if (Objects.isNull(feignDTO)) {
                return Result.ok(new SandboxMerchantApiSettingExchangeDTO());
            }

            SandboxMerchantApiSettingExchangeDTO settingDTO = new SandboxMerchantApiSettingExchangeDTO();
            settingDTO.setMerchantId(feignDTO.getMerchantId());
            settingDTO.setMerchantCode(feignDTO.getMerchantCode()); //ok
            settingDTO.setMerchantName(feignDTO.getMerchantName());
            settingDTO.setMerchantSecret(feignDTO.getMerchantSecret());
            settingDTO.setPayInNotifyAddress(feignDTO.getFinishPaymentUrl());
            settingDTO.setPayOutNotifyAddress(feignDTO.getFinishCashUrl());
            settingDTO.setMerchantPublicKey(feignDTO.getPublicKey());
            settingDTO.setPlatformPublicKey(sandboxPlatformKeyConfig.parsePublicKey());

            Optional<JSONObject> jsonObj = Optional.of(feignDTO).map(SandboxMerchantConfigExchangeDTO::getIpWhiteList)
                    .map(JSONUtil::parseObj);
            jsonObj.map(e -> e.getStr("payment")).map(e -> e.split(";")).map(Arrays::asList)
                    .ifPresent(settingDTO::setPayInIpWhiteList);
            jsonObj.map(e -> e.getStr("payout")).map(e -> e.split(";")).map(Arrays::asList)
                    .ifPresent(settingDTO::setPayOutIpWhiteList);
            jsonObj.map(e -> e.getStr("balance")).map(e -> e.split(";")).map(Arrays::asList)
                    .ifPresent(settingDTO::setCheckBalanceIpWhiteList);

            return Result.ok(settingDTO);
        });
    }



    public Mono<Result<Boolean>> updateSandboxMerchantConfig(MerchantApiSettingUpdateExchangeParam dto) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.putOpt("payment", String.join(";", dto.getPayInIpWhiteList()));
        jsonObj.putOpt("payout", String.join(";", dto.getPayOutIpWhiteList()));
        jsonObj.putOpt("balance", String.join(";", dto.getCheckBalanceIpWhiteList()));

        SandboxMerchantConfigUpdateExchangeParam feignParam = new SandboxMerchantConfigUpdateExchangeParam();
        feignParam.setMerchantId(SANDBOX + dto.getMerchantId());
        feignParam.setIpWhiteList(JSONUtil.toJsonStr(jsonObj));
        feignParam.setFinishPaymentUrl(dto.getPayInNotifyAddress());
        feignParam.setFinishCashUrl(dto.getPayOutNotifyAddress());
        feignParam.setPublicKey(dto.getMerchantPublicKey());
        return merchantApiService.updateSandboxMerchantConfig(feignParam);
    }
}
