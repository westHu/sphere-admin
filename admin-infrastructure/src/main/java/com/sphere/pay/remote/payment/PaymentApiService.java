package com.sphere.pay.remote.payment;

import com.sphere.pay.remote.payment.dto.PaymentChannelBalanceFeignDTO;
import com.sphere.pay.remote.payment.dto.PaymentChannelDropFeignDTO;
import com.sphere.pay.remote.payment.dto.PaymentChannelFeignDTO;
import com.sphere.pay.remote.payment.dto.PaymentMethodDropFeignDTO;
import com.sphere.pay.remote.payment.dto.PaymentMethodFeignDTO;
import com.sphere.pay.remote.payment.dto.PaymentTimelyStatisticsIndexFeignDTO;
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
import com.sphere.pay.remote.payment.param.PaymentTimelyStatisticsIndexFeignParam;
import com.sphere.pay.result.PageResult;
import com.sphere.pay.result.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@HttpExchange
public interface PaymentApiService {


    //----- 渠道 ------
    /**
     * 渠道下拉框
     */
    @PostExchange("/dropPaymentChannelList")
    Mono<Result<List<PaymentChannelDropFeignDTO>>> dropPaymentChannelList();

    /**
     * 分页查询渠道
     */
    @PostExchange("/pagePaymentChannelList")
    Mono<PageResult<PaymentChannelFeignDTO>> pagePaymentChannelList(@RequestBody PaymentChannelPageExchangeParam param);

    /**
     * 更新渠道信息
     */
    @PostExchange("/updatePaymentChannel")
    Mono<Result<Boolean>> updatePaymentChannel(@RequestBody PaymentChannelUpdateFeignParam param);

    /**
     * 开关渠道
     */
    @PostExchange("/openOrClosePaymentChannel")
    Mono<Result<Boolean>> openOrClosePaymentChannel(@RequestBody PaymentChannelStatusFeignParam param);



    //----- 支付方式 ------


    /**
     * 支付方式下拉框
     */
    @PostExchange("/dropPaymentMethodList")
    Mono<Result<List<PaymentMethodDropFeignDTO>>> dropPaymentMethodList();

    /**
     * 分页查询支付方式
     */
    @PostExchange("/pagePaymentMethodList")
    Mono<PageResult<PaymentMethodFeignDTO>> pagePaymentMethodList(@RequestBody PaymentMethodPageExchangeParam param);


    /**
     * 更新支付方式
     */
    @PostExchange("/updatePaymentMethod")
    Mono<Result<Boolean>> updatePaymentMethod(@RequestBody PaymentMethodUpdateFeignParam param);

    /**
     * 开关支付方式
     */
    @PostExchange("/openOrClosePaymentMethod")
    Mono<Result<Boolean>> openOrClosePaymentMethod(@RequestBody PaymentMethodStatusFeignParam param);





    //----- 渠道&支付方式 ------


    /**
     * 开关渠道支付方式
     */
    @PostExchange("/openOrClosePaymentChannelMethod")
    Mono<Result<Boolean>> openOrClosePaymentChannelMethod(@RequestBody List<PaymentChannelMethodStatusFeignParam> paramList);


    /**
     * 增加渠道支付方式
     */
    @PostExchange("/addPaymentChannelMethod")
    Mono<Result<Boolean>> addPaymentChannelMethod(@RequestBody PaymentChannelMethodAddFeignParam param);



    /**
     * 更新渠道支付方式
     */
    @PostExchange("/updatePaymentChannelMethod")
    Mono<Result<Boolean>> updatePaymentChannelMethod(@RequestBody PaymentChannelMethodUpdateFeignParam param);




    //----- 其他 ------

    /**
     * 查询渠道余额列表
     */
    @PostExchange("/pageChannelBalanceList")
    Mono<Result<List<PaymentChannelBalanceFeignDTO>>> pageChannelBalanceList(PaymentChannelBalanceFeignParam param);


    /**
     * 首页 渠道信息
     */
    @PostExchange("/getPaymentTimelyStatistics4Index")
    Mono<Result<PaymentTimelyStatisticsIndexFeignDTO>> getPaymentTimelyStatistics4Index(@RequestBody PaymentTimelyStatisticsIndexFeignParam param);

}
