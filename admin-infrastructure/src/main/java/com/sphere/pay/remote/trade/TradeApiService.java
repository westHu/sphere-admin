package com.sphere.pay.remote.trade;

import com.sphere.pay.remote.trade.dto.TradeChannelDailySnapchatExchangeDTO;
import com.sphere.pay.remote.trade.dto.TradeMerchantDailySnapchatExchangeDTO;
import com.sphere.pay.remote.trade.dto.TradePaymentOrderExchangeDTO;
import com.sphere.pay.remote.trade.dto.TradePaymentOrderPageExchangeDTO;
import com.sphere.pay.remote.trade.dto.TradePayoutOrderFeignDTO;
import com.sphere.pay.remote.trade.dto.TradePayoutOrderPageExchangeDTO;
import com.sphere.pay.remote.trade.dto.TradePayoutReceiptExchangeDTO;
import com.sphere.pay.remote.trade.dto.TradePlatformDailySnapchatExchangeDTO;
import com.sphere.pay.remote.trade.dto.TradeRechargeOrderPageExchangeDTO;
import com.sphere.pay.remote.trade.dto.TradeTimelyStatisticsIndexExchangeDTO;
import com.sphere.pay.remote.trade.dto.TradeTransferOrderExchangeDTO;
import com.sphere.pay.remote.trade.dto.TradeTransferOrderPageExchangeDTO;
import com.sphere.pay.remote.trade.dto.TradeWithdrawOrderPageExchangeDTO;
import com.sphere.pay.remote.trade.param.TradeNoExchangeParam;
import com.sphere.pay.remote.trade.param.TradePaymentOrderPageExchangeParam;
import com.sphere.pay.remote.trade.param.TradePayoutOrderPageExchangeParam;
import com.sphere.pay.remote.trade.param.TradeRechargeOrderPageExchangeParam;
import com.sphere.pay.remote.trade.param.TradeReviewExchangeParam;
import com.sphere.pay.remote.trade.param.TradeStatisticsChannelExchangeParam;
import com.sphere.pay.remote.trade.param.TradeStatisticsMerchantExchangeParam;
import com.sphere.pay.remote.trade.param.TradeStatisticsPlatformExchangeParam;
import com.sphere.pay.remote.trade.param.TradeTimelyStatisticsIndexExchangeParam;
import com.sphere.pay.remote.trade.param.TradeTransferExchangeParam;
import com.sphere.pay.remote.trade.param.TradeTransferOrderPageExchangeParam;
import com.sphere.pay.remote.trade.param.TradeWithdrawOrderPageExchangeParam;
import com.sphere.pay.result.PageResult;
import com.sphere.pay.result.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

@HttpExchange
public interface TradeApiService {


    //----- 收款 ------

    /**
     * 分页查询收款订单
     */
    @PostExchange("/pagePaymentOrderList")
    Mono<PageResult<TradePaymentOrderPageExchangeDTO>> pagePaymentOrderList(@RequestBody TradePaymentOrderPageExchangeParam param);

    /**
     * 导出收款订单
     */
    @PostExchange("/exportPaymentOrderList")
    Mono<Result<String>> exportPaymentOrderList(@RequestBody TradePaymentOrderPageExchangeParam param);

    /**
     * 查询收款订单详情
     */
    @PostExchange("/getPaymentOrder")
    Mono<Result<TradePaymentOrderExchangeDTO>> getPaymentOrder(@RequestBody TradeNoExchangeParam param);

    /**
     * 收款补单
     */
    @PostExchange("/paymentSupplement")
    Mono<Result<Boolean>> paymentSupplement(@RequestBody TradeNoExchangeParam param);

    /**
     * 收款退单
     */
    @PostExchange("/paymentRefund")
    Mono<Result<Boolean>> paymentRefund(@RequestBody TradeNoExchangeParam param);



    //----- 出款 ------


    /**
     * 分页查询出款订单
     */
    @PostExchange("/pagePayoutOrderList")
    Mono<PageResult<TradePayoutOrderPageExchangeDTO>> pagePayoutOrderList(@RequestBody TradePayoutOrderPageExchangeParam param);

    /**
     * 导出出款订单
     */
    @PostExchange("/exportPayoutOrderList")
    Mono<Result<String>> exportPayoutOrderList(@RequestBody TradePayoutOrderPageExchangeParam param);

    /**
     * 查询出款订单详情
     */
    @PostExchange("/getPayoutOrder")
    Mono<Result<TradePayoutOrderFeignDTO>> getPayoutOrder(@RequestBody TradeNoExchangeParam param);

    /**
     * 出款补单
     */
    @PostExchange("/payoutSupplement")
    Mono<Result<Boolean>> payoutSupplement(@RequestBody TradeNoExchangeParam param);

    /**
     * 出款退单
     */
    @PostExchange("/payoutRefund")
    Mono<Result<Boolean>> payoutRefund(@RequestBody TradeNoExchangeParam param);

    /**
     * 出款交易凭证
     */
    @PostExchange("/getPayoutReceipt")
    Mono<Result<TradePayoutReceiptExchangeDTO>> getPayoutReceipt(@RequestBody TradeNoExchangeParam param);


    //----- 转账 ------


    /**
     * 分页转账订单
     */
    @PostExchange("/pageTransferOrderList")
    Mono<PageResult<TradeTransferOrderPageExchangeDTO>> pageTransferOrderList(@RequestBody TradeTransferOrderPageExchangeParam param);

    /**
     * 导出转账订单
     */
    @PostExchange("/exportTransferOrderList")
    Mono<Result<String>> exportTransferOrderList(@RequestBody TradeTransferOrderPageExchangeParam param);


    /**
     * 查询收款订单详情
     */
    @PostExchange("/getTransferOrder")
    Mono<Result<TradeTransferOrderExchangeDTO>> getTransferOrder(TradeNoExchangeParam param);

    /**
     * 转账操作
     */
    @PostExchange("/transfer")
    Mono<Result<Boolean>> transfer(@RequestBody TradeTransferExchangeParam param);



    //----- 充值 ------


    /**
     * 分页查询充值订单
     */
    @PostExchange("/pageRechargeOrderList")
    Mono<PageResult<TradeRechargeOrderPageExchangeDTO>> pageRechargeOrderList(@RequestBody TradeRechargeOrderPageExchangeParam param);

    /**
     * 导出充值订单
     */
    @PostExchange("/exportRechargeOrderList")
    Mono<Result<String>> exportRechargeOrderList(@RequestBody TradeRechargeOrderPageExchangeParam param);

    /**
     * 人工发起审核
     */
    @PostExchange("/reviewRecharge")
    Mono<Result<Boolean>> reviewRecharge(@RequestBody TradeNoExchangeParam param);



    //----- 提现 ------

    /**
     * 分页查询提现订单
     */
    @PostExchange("/pageWithdrawOrderList")
    Mono<PageResult<TradeWithdrawOrderPageExchangeDTO>> pageWithdrawOrderList(@RequestBody TradeWithdrawOrderPageExchangeParam param);

    /**
     * 导出提现订单
     */
    @PostExchange("/exportWithdrawOrderList")
    Mono<Result<String>> exportWithdrawOrderList(@RequestBody TradeWithdrawOrderPageExchangeParam param);


    //----- 数据分析 ------

    /**
     * 平台交易分析数据 - 收款、出款
     */
    @PostExchange("/getPlatformTradeStatistics")
    Mono<PageResult<TradePlatformDailySnapchatExchangeDTO>> getPlatformTradeStatistics(@RequestBody TradeStatisticsPlatformExchangeParam param);

    /**
     * 导出 平台交易分析数据 - 收款、出款
     */
    @PostExchange("/exportPlatformTradeStatistics")
    Mono<Result<String>> exportPlatformTradeStatistics(@RequestBody TradeStatisticsPlatformExchangeParam param);

    /**
     * 通道交易分析数据 -收款、出款
     */
    @PostExchange("/getChannelTradeStatistics")
    Mono<PageResult<TradeChannelDailySnapchatExchangeDTO>> getChannelTradeStatistics(@RequestBody TradeStatisticsChannelExchangeParam param);

    /**
     * 导出 通道交易分析数据 -收款、出款
     */
    @PostExchange("/exportChannelTradeStatistics")
    Mono<Result<String>> exportChannelTradeStatistics(@RequestBody TradeStatisticsChannelExchangeParam param);

    /**
     * 商户交易分析数据 - 收款、出款
     */
    @PostExchange("/getMerchantTradeStatistics")
    Mono<PageResult<TradeMerchantDailySnapchatExchangeDTO>> getMerchantTradeStatistics(@RequestBody TradeStatisticsMerchantExchangeParam param);

    /**
     * 导出 商户交易分析数据 - 收款、出款
     */
    @PostExchange("/exportMerchantTradeStatistics")
    Mono<Result<String>> exportMerchantTradeStatistics(@RequestBody TradeStatisticsMerchantExchangeParam param);

    /**
     * 首页 交易信息
     */
    @PostExchange("/getTradeTimelyStatistics4Index")
    Mono<Result<TradeTimelyStatisticsIndexExchangeDTO>> getTradeTimelyStatistics4Index(@RequestBody TradeTimelyStatisticsIndexExchangeParam param);




    //----- 其他 ------



    /**
     * 订单审核
     */
    @PostExchange("/tradeReview")
    Mono<Result<Boolean>> tradeReview(@RequestBody TradeReviewExchangeParam param);

    /**
     * 订单补发回调通知
     */
    @PostExchange("/callback")
    Mono<Result<Boolean>> callback(@RequestBody TradeNoExchangeParam param);


}
