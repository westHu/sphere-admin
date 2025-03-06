package com.sphere.pay.controller.merchant;

import cn.hutool.json.JSONUtil;
import com.sphere.pay.controller.request.MerchantOptLogPageReq;
import com.sphere.pay.convert.MerchantOptLogConverter;
import com.sphere.pay.dto.MerchantOptLogDTO;
import com.sphere.pay.param.MerchantOptLogPageParam;
import com.sphere.pay.result.PageResult;
import com.sphere.pay.service.merchant.MerchantOptLogService;
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
public class MerchantOptLogController {

    @Resource
    MerchantOptLogConverter merchantOptLogConverter;
    @Resource
    MerchantOptLogService merchantOptLogService;

    /**
     * 商户操作日志
     */
    @PostMapping("/pageMerchantOptLogList")
    public Mono<PageResult<MerchantOptLogDTO>> pageMerchantOptLogList(@RequestBody @Validated MerchantOptLogPageReq req) {
        log.info("pageMerchantOptLogList req={}", JSONUtil.toJsonStr(req));
        MerchantOptLogPageParam param = merchantOptLogConverter.convertMerchantOptLogPageParam(req);
        return Mono.just(merchantOptLogService.pageMerchantOptLogList(param));
    }

}
