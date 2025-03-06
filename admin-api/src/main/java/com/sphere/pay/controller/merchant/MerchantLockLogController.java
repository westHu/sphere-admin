package com.sphere.pay.controller.merchant;

import cn.hutool.json.JSONUtil;
import com.sphere.pay.SysRequestManager;
import com.sphere.pay.controller.request.MerchantLockLogPageReq;
import com.sphere.pay.controller.request.MerchantUnlockReq;
import com.sphere.pay.convert.MerchantLockLogConverter;
import com.sphere.pay.dto.MerchantLockLogDTO;
import com.sphere.pay.param.MerchantLockLogPageParam;
import com.sphere.pay.param.MerchantUnlockParam;
import com.sphere.pay.result.PageResult;
import com.sphere.pay.result.Result;
import com.sphere.pay.service.merchant.MerchantLockLogService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 商户管理 API
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class MerchantLockLogController {

    @Resource
    MerchantLockLogConverter merchantLockLogConverter;
    @Resource
    MerchantLockLogService merchantLockLogService;

    /**
     * 商户解除登录锁定
     */
    @PostMapping("/pageMerchantLockLogList")
    public Mono<PageResult<MerchantLockLogDTO>> pageMerchantLockLogList(@RequestBody @Validated MerchantLockLogPageReq req) {
        log.info("pageMerchantLockLogList req={}", JSONUtil.toJsonStr(req));
        MerchantLockLogPageParam param = merchantLockLogConverter.conventMerchantLockLogPageParam(req);
        return Mono.just(merchantLockLogService.pageMerchantLockLogList(param));
    }

    /**
     * 商户解除登录锁定
     */
    @PostMapping("/merchantUnLock")
    public Mono<Result<Boolean>> unLock4MerchantLogin(@RequestBody @Validated MerchantUnlockReq req, ServerWebExchange exchange) {
        log.info("unLock4MerchantLogin req={}", JSONUtil.toJsonStr(req));
        MerchantUnlockParam param = merchantLockLogConverter.convertMerchantUnlockParam(req);
        req.setUnlockName(SysRequestManager.getCurrentUser().getUserName());
        return Mono.just(Result.ok(merchantLockLogService.unLock4MerchantLogin(param)));
    }

}
