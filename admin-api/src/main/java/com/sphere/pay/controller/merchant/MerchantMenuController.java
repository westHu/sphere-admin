package com.sphere.pay.controller.merchant;


import cn.hutool.json.JSONUtil;
import com.sphere.pay.controller.request.MerchantMenuAddReq;
import com.sphere.pay.controller.request.MerchantMenuTreeReq;
import com.sphere.pay.controller.request.MerchantMenuUpdateReq;
import com.sphere.pay.convert.MerchantMenuConverter;
import com.sphere.pay.dto.MerchantMenuTreeDTO;
import com.sphere.pay.param.MerchantMenuAddParam;
import com.sphere.pay.param.MerchantMenuTreeParam;
import com.sphere.pay.param.MerchantMenuUpdateParam;
import com.sphere.pay.result.Result;
import com.sphere.pay.service.merchant.MerchantMenuService;
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
 * 商户管理 API
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class MerchantMenuController {

    @Resource
    MerchantMenuConverter merchantMenuConverter;
    @Resource
    MerchantMenuService merchantMenuService;

    /**
     * 菜单列表
     */
    @PostMapping("/getMerchantMenuTreeList")
    public Mono<Result<List<MerchantMenuTreeDTO>>> getMerchantMenuTreeList(@RequestBody @Validated MerchantMenuTreeReq req) {
        log.info("getMerchantMenuTreeList req={}", JSONUtil.toJsonStr(req));
        MerchantMenuTreeParam param = merchantMenuConverter.convertMerchantMenuTreeParam(req);
        return Mono.just(Result.ok(merchantMenuService.getMerchantMenuTreeList(param)));
    }

    /**
     * 新增菜单
     */
    @PostMapping("/addMerchantMenu")
    public Mono<Result<Boolean>> addMerchantMenu(@RequestBody @Validated MerchantMenuAddReq req) {
        log.info("addMerchantMenu req={}", JSONUtil.toJsonStr(req));
        MerchantMenuAddParam param = merchantMenuConverter.convertMerchantMenuAddParam(req);
        return Mono.just(Result.ok(merchantMenuService.addMerchantMenu(param)));
    }

    /**
     * 更新菜单
     */
    @PostMapping("/updateMerchantMenu")
    public Mono<Result<Boolean>> updateMerchantMenu(@RequestBody @Validated MerchantMenuUpdateReq req) {
        log.info("updateMerchantMenu req={}", JSONUtil.toJsonStr(req));
        MerchantMenuUpdateParam param = merchantMenuConverter.convertMerchantMenuUpdateParam(req);
        return Mono.just(Result.ok(merchantMenuService.updateMerchantMenu(param)));
    }

}
