package com.sphere.pay.controller.merchant;

import cn.hutool.json.JSONUtil;
import com.sphere.pay.controller.request.MerchantLoginLogPageReq;
import com.sphere.pay.convert.MerchantLoginLogConverter;
import com.sphere.pay.dto.MerchantLoginLogDTO;
import com.sphere.pay.param.MerchantLoginLogPageParam;
import com.sphere.pay.result.PageResult;
import com.sphere.pay.service.merchant.MerchantLoginLogService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 商户管理 API
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class MerchantLoginLogController {

    @Resource
    MerchantLoginLogConverter merchantLoginLogConverter;
    @Resource
    MerchantLoginLogService merchantLoginLogService;

    /**
     * 商户登录日志列表
     */
    @PostMapping("/pageMerchantLoginLogList")
    public Mono<PageResult<MerchantLoginLogDTO>> pageMerchantLockLogList(@RequestBody @Validated MerchantLoginLogPageReq req) {
        log.info("pageMerchantLoginLogList req={}", JSONUtil.toJsonStr(req));
        MerchantLoginLogPageParam param = merchantLoginLogConverter.convertMerchantLoginLogPageParam(req);
        return Mono.just(merchantLoginLogService.pageMerchantLoginLogList(param));
    }

}
