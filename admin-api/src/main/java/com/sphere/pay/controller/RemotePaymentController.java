package com.sphere.pay.controller;

import cn.hutool.json.JSONUtil;
import com.sphere.pay.config.opt.OptLogFlag;
import com.sphere.pay.remote.payment.PaymentApiService;
import com.sphere.pay.remote.payment.dto.PaymentChannelBalanceFeignDTO;
import com.sphere.pay.remote.payment.dto.PaymentChannelDropFeignDTO;
import com.sphere.pay.remote.payment.dto.PaymentChannelFeignDTO;
import com.sphere.pay.remote.payment.dto.PaymentMethodDropFeignDTO;
import com.sphere.pay.remote.payment.dto.PaymentMethodFeignDTO;
import com.sphere.pay.remote.payment.param.PaymentChannelBalanceFeignParam;
import com.sphere.pay.remote.payment.param.PaymentChannelMethodAddFeignParam;
import com.sphere.pay.remote.payment.param.PaymentChannelMethodStatusFeignParam;
import com.sphere.pay.remote.payment.param.PaymentChannelMethodUpdateFeignParam;
import com.sphere.pay.remote.payment.param.PaymentChannelPageExchangeParam;
import com.sphere.pay.remote.payment.param.PaymentChannelStatusFeignParam;
import com.sphere.pay.remote.payment.param.PaymentChannelUpdateFeignParam;
import com.sphere.pay.remote.payment.param.PaymentMethodPageExchangeParam;
import com.sphere.pay.remote.payment.param.PaymentMethodStatusFeignParam;
import com.sphere.pay.remote.payment.param.PaymentMethodUpdateFeignParam;
import com.sphere.pay.result.PageResult;
import com.sphere.pay.result.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 支付API
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class RemotePaymentController {

    @Resource
    PaymentApiService paymentApiService;

    /**
     * 渠道下拉框接口
     */
    @PostMapping("/dropPaymentChannelList")
    public Mono<Result<List<PaymentChannelDropFeignDTO>>> dropPaymentChannelList() {
        return paymentApiService.dropPaymentChannelList();
    }

    /**
     * 支付方式下拉框接口
     */
    @PostMapping("/dropPaymentMethodList")
    public Mono<Result<List<PaymentMethodDropFeignDTO>>> dropPaymentMethodList() {
        return paymentApiService.dropPaymentMethodList();
    }

    /**
     * 分页查询渠道
     */
    @PostMapping("/pagePaymentChannelList")
    public Mono<PageResult<PaymentChannelFeignDTO>> pagePaymentChannelList(@RequestBody PaymentChannelPageExchangeParam param) {
        log.info("pagePaymentChannelList param={}", JSONUtil.toJsonStr(param));
        return paymentApiService.pagePaymentChannelList(param);
    }

    /**
     * 分页查询支付方式
     */
    @PostMapping("/pagePaymentMethodList")
    public Mono<PageResult<PaymentMethodFeignDTO>> pagePaymentMethodList(@RequestBody PaymentMethodPageExchangeParam param) {
        log.info("pagePaymentMethodList param={}", JSONUtil.toJsonStr(param));
        return paymentApiService.pagePaymentMethodList(param);
    }

    /**
     * 开关或者关闭渠道 记录操作日志
     */
    @OptLogFlag
    @PostMapping(value = "/openOrClosePaymentChannel")
    public Mono<Result<Boolean>>openOrClosePaymentChannel(@RequestBody PaymentChannelStatusFeignParam param) {
        log.info("openOrClosePaymentChannel param={}", JSONUtil.toJsonStr(param));
        return paymentApiService.openOrClosePaymentChannel(param);
    }


    /**
     * 开关或者关闭支付方式 记录操作日志
     */
    @OptLogFlag
    @PostMapping(value = "/openOrClosePaymentMethod")
    public Mono<Result<Boolean>>openOrClosePaymentMethod(@RequestBody PaymentMethodStatusFeignParam param) {
        log.info("openOrClosePaymentMethod param={}", JSONUtil.toJsonStr(param));
        return paymentApiService.openOrClosePaymentMethod(param);
    }

    /**
     * 开关或者关闭渠道支付方式 记录操作日志
     */
    @OptLogFlag
    @PostMapping(value = "/openOrClosePaymentChannelMethod")
    public Mono<Result<Boolean>>openOrClosePaymentChannelMethod(@RequestBody List<PaymentChannelMethodStatusFeignParam> paramList) {
        log.info("openOrClosePaymentChannelMethod paramList={}", JSONUtil.toJsonStr(paramList));
        return paymentApiService.openOrClosePaymentChannelMethod(paramList);
    }

    /**
     * 更新渠道信息 记录操作日志
     */
    @OptLogFlag
    @PostMapping("/updatePaymentChannel")
    public Mono<Result<Boolean>>updatePaymentChannel(@RequestBody PaymentChannelUpdateFeignParam param) {
        log.info("updatePaymentChannel param={}", JSONUtil.toJsonStr(param));
        return paymentApiService.updatePaymentChannel(param);
    }

    /**
     * 更新支付方式 记录操作日志
     */
    @OptLogFlag
    @PostMapping(value = "/updatePaymentMethod")
    public Mono<Result<Boolean>>updatePaymentMethod(@RequestBody PaymentMethodUpdateFeignParam param) {
        log.info("UpdatePaymentMethod param={}", JSONUtil.toJsonStr(param));
        return paymentApiService.updatePaymentMethod(param);
    }

    /**
     * 新增渠道支付方式 记录操作日志
     */
    @OptLogFlag
    @PostMapping(value = "/addPaymentChannelMethod")
    public Mono<Result<Boolean>>addPaymentChannelMethod(@RequestBody PaymentChannelMethodAddFeignParam param) {
        log.info("addPaymentChannelMethod param={}", JSONUtil.toJsonStr(param));
        return paymentApiService.addPaymentChannelMethod(param);
    }

    /**
     * 更新渠道支付方式 记录操作日志
     */
    @OptLogFlag
    @PostMapping(value = "/updatePaymentChannelMethod")
    public Mono<Result<Boolean>>updatePaymentChannelMethod(@RequestBody PaymentChannelMethodUpdateFeignParam param) {
        log.info("UpdatePaymentChannelMethod param={}", JSONUtil.toJsonStr(param));
        return paymentApiService.updatePaymentChannelMethod(param);
    }

    /**
     * 查询 渠道余额列表
     */
    @PostMapping("/pageChannelBalanceList")
    public Mono<Result<List<PaymentChannelBalanceFeignDTO>>>pageChannelBalanceList(@RequestBody PaymentChannelBalanceFeignParam param) {
        log.info("pageChannelBalanceList param={}", JSONUtil.toJsonStr(param));
        return paymentApiService.pageChannelBalanceList(param);
    }

}
