package com.sphere.pay.controller;


import cn.hutool.json.JSONUtil;
import com.sphere.pay.SysRequestManager;
import com.sphere.pay.config.limit.LimitFlag;
import com.sphere.pay.config.limit.LimitTypeEnum;
import com.sphere.pay.config.opt.OptLogFlag;
import com.sphere.pay.config.valid.GoogleAuthenticatorValid;
import com.sphere.pay.config.valid.PasswordValid;
import com.sphere.pay.controller.request.TradeAuthCodeReq;
import com.sphere.pay.controller.request.TradeTransferReq;
import com.sphere.pay.remote.trade.TradeApiService;
import com.sphere.pay.remote.trade.dto.TradeChannelDailySnapchatExchangeDTO;
import com.sphere.pay.remote.trade.dto.TradeMerchantDailySnapchatExchangeDTO;
import com.sphere.pay.remote.trade.dto.TradePaymentOrderExchangeDTO;
import com.sphere.pay.remote.trade.dto.TradePaymentOrderPageExchangeDTO;
import com.sphere.pay.remote.trade.dto.TradePayoutOrderFeignDTO;
import com.sphere.pay.remote.trade.dto.TradePayoutOrderPageExchangeDTO;
import com.sphere.pay.remote.trade.dto.TradePayoutReceiptExchangeDTO;
import com.sphere.pay.remote.trade.dto.TradePlatformDailySnapchatExchangeDTO;
import com.sphere.pay.remote.trade.dto.TradeRechargeOrderPageExchangeDTO;
import com.sphere.pay.remote.trade.dto.TradeTransferOrderExchangeDTO;
import com.sphere.pay.remote.trade.dto.TradeTransferOrderPageExchangeDTO;
import com.sphere.pay.remote.trade.dto.TradeWithdrawOrderPageExchangeDTO;
import com.sphere.pay.remote.trade.param.TradeNoExchangeParam;
import com.sphere.pay.remote.trade.param.TradePaymentOrderPageExchangeParam;
import com.sphere.pay.remote.trade.param.TradePayoutOrderPageExchangeParam;
import com.sphere.pay.remote.trade.param.TradeRechargeOrderPageExchangeParam;
import com.sphere.pay.remote.trade.param.TradeStatisticsChannelExchangeParam;
import com.sphere.pay.remote.trade.param.TradeStatisticsMerchantExchangeParam;
import com.sphere.pay.remote.trade.param.TradeStatisticsPlatformExchangeParam;
import com.sphere.pay.remote.trade.param.TradeTransferOrderPageExchangeParam;
import com.sphere.pay.remote.trade.param.TradeWithdrawOrderPageExchangeParam;
import com.sphere.pay.result.PageResult;
import com.sphere.pay.result.Result;
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
 * 交易API
 *
 * @author dh
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class RemoteTradeController {

    @Resource
    TradeApiService tradeApiService;

    /**
     * 收款订单列表
     */
    @LimitFlag(limitType = LimitTypeEnum.IP)
    @PostMapping("/pagePaymentOrderList")
    public Mono<PageResult<TradePaymentOrderPageExchangeDTO>> pagePayOrderList(@RequestBody @Validated
                                                                           TradePaymentOrderPageExchangeParam param) {
        log.info("pagePaymentOrderList param={}", JSONUtil.toJsonStr(param));
        return tradeApiService.pagePaymentOrderList(param);
    }

    /**
     * 导出收款订单 记录操作日志
     */
    @OptLogFlag
    @LimitFlag(limitType = LimitTypeEnum.IP)
    @PostMapping("/exportPaymentOrderList")
    public Mono<Result<String>> exportPayOrderList(@RequestBody @Validated TradePaymentOrderPageExchangeParam param) {
        log.info("exportPaymentOrderList param={}", JSONUtil.toJsonStr(param));
        return tradeApiService.exportPaymentOrderList(param);
    }

    /**
     * 收款订单详情
     */
    @LimitFlag(limitType = LimitTypeEnum.IP)
    @PostMapping("/getPaymentOrder")
    public Mono<Result<TradePaymentOrderExchangeDTO>> getPayOrder(@RequestBody @Validated TradeNoExchangeParam param) {
        log.info("getPaymentOrder param={}", JSONUtil.toJsonStr(param));
        return tradeApiService.getPaymentOrder(param);
    }

    /**
     * 收款补单 记录操作日志
     */
    @OptLogFlag
    @GoogleAuthenticatorValid
    @PostMapping(value = "/paymentSupplement")
    public Mono<Result<Boolean>> paySupplement(@RequestBody @Validated TradeAuthCodeReq param, ServerWebExchange exchange) {
        log.info("paymentSupplement param={}", JSONUtil.toJsonStr(param));
        param.setOperator(SysRequestManager.getCurrentUser().getUserName());
        return tradeApiService.paymentSupplement(param);
    }

    /**
     * 收款退单 记录操作日志
     */
    @OptLogFlag
    @GoogleAuthenticatorValid
    @PostMapping(value = "/paymentRefund")
    public Mono<Result<Boolean>> payRefund(@RequestBody @Validated TradeAuthCodeReq param, ServerWebExchange exchange) {
        log.info("paymentRefund param={}", JSONUtil.toJsonStr(param));
        param.setOperator(SysRequestManager.getCurrentUser().getUserName());
        return tradeApiService.paymentRefund(param);
    }


    /**
     * 出款订单列表
     */
    @LimitFlag(limitType = LimitTypeEnum.IP)
    @PostMapping("/pagePayoutOrderList")
    public Mono<PageResult<TradePayoutOrderPageExchangeDTO>> pageCashOrderList(@RequestBody @Validated
                                                                             TradePayoutOrderPageExchangeParam param) {
        log.info("pagePayoutOrderList param={}", JSONUtil.toJsonStr(param));
        return tradeApiService.pagePayoutOrderList(param);
    }

    /**
     * 导出出款订单列表 记录操作日志
     */
    @OptLogFlag
    @LimitFlag(limitType = LimitTypeEnum.IP)
    @PostMapping("/exportPayoutOrderList")
    public Mono<Result<String>> exportCashOrderList(@RequestBody @Validated TradePayoutOrderPageExchangeParam param) {
        log.info("exportPayoutOrderList param={}", JSONUtil.toJsonStr(param));
        return tradeApiService.exportPayoutOrderList(param);
    }

    /**
     * 出款订单详情
     */
    @LimitFlag(limitType = LimitTypeEnum.IP)
    @PostMapping("/getPayoutOrder")
    public Mono<Result<TradePayoutOrderFeignDTO>> getCashOrder(@RequestBody @Validated TradeNoExchangeParam param) {
        log.info("getPayoutOrder param={}", JSONUtil.toJsonStr(param));
        return tradeApiService.getPayoutOrder(param);
    }

    /**
     * 出款补单 记录操作日志
     */
    @OptLogFlag
    @GoogleAuthenticatorValid
    @PostMapping(value = "/payoutSupplement")
    public Mono<Result<Boolean>> payoutSupplement(@RequestBody @Validated TradeAuthCodeReq param, ServerWebExchange exchange) {
        log.info("payoutSupplement param={}", JSONUtil.toJsonStr(param));
        param.setOperator(SysRequestManager.getCurrentUser().getUserName());
        return tradeApiService.payoutSupplement(param);
    }

    /**
     * 出款退单 记录操作日志
     */
    @OptLogFlag
    @GoogleAuthenticatorValid
    @PostMapping(value = "/payoutRefund")
    public Mono<Result<Boolean>> payoutRefund(@RequestBody @Validated TradeAuthCodeReq param, ServerWebExchange exchange) {
        log.info("payoutRefund param={}", JSONUtil.toJsonStr(param));
        param.setOperator(SysRequestManager.getCurrentUser().getUserName());
        return tradeApiService.payoutRefund(param);
    }

    /**
     * 分页查询转账订单列表
     */
    @LimitFlag(limitType = LimitTypeEnum.IP)
    @PostMapping("/pageTransferOrderList")
    public Mono<PageResult<TradeTransferOrderPageExchangeDTO>> pageTransferOrderList(@RequestBody @Validated
                                                                                  TradeTransferOrderPageExchangeParam param) {
        log.info("pageTransferOrderList param={}", JSONUtil.toJsonStr(param));
        return tradeApiService.pageTransferOrderList(param);
    }


    /**
     * 导出转账订单列表 记录操作日志
     */
    @OptLogFlag
    @LimitFlag(limitType = LimitTypeEnum.IP)
    @PostMapping("/exportTransferOrderList")
    public Mono<Result<String>> exportTransferOrderList(@RequestBody @Validated TradeTransferOrderPageExchangeParam param) {
        log.info("exportTransferOrderList param={}", JSONUtil.toJsonStr(param));
        return tradeApiService.exportTransferOrderList(param);
    }


    /**
     * 查询转账订单详情
     */
    @LimitFlag(limitType = LimitTypeEnum.IP)
    @PostMapping("/getTransferOrder")
    public Mono<Result<TradeTransferOrderExchangeDTO>> getTransferOrder(@RequestBody @Validated TradeNoExchangeParam param) {
        log.info("getTransferOrder param={}", JSONUtil.toJsonStr(param));
        return tradeApiService.getTransferOrder(param);
    }

    /**
     * 转账操作
     */
    @PasswordValid
    @PostMapping("/transfer")
    public Mono<Result<Boolean>> transfer(@RequestBody @Validated TradeTransferReq param, ServerWebExchange exchange) {
        log.info("transfer param={}", JSONUtil.toJsonStr(param));
        param.setApplyOperator(SysRequestManager.getCurrentUser().getUserName());
        return tradeApiService.transfer(param);
    }

    /**
     * 订单补发回调通知 记录操作日志
     */
    @OptLogFlag
    @PostMapping("/callback")
    public Mono<Result<Boolean>> callback(@RequestBody @Validated TradeNoExchangeParam param, ServerWebExchange exchange) {
        log.info("callback param={}", JSONUtil.toJsonStr(param));
        param.setOperator(SysRequestManager.getCurrentUser().getUserName());
        return tradeApiService.callback(param);
    }

    /**
     * 统计中心 平台日报表 （收款、出款）
     */
    @PostMapping("/getPlatformDailyReport")
    public Mono<PageResult<TradePlatformDailySnapchatExchangeDTO>> getPlatformDailyReport(
            @RequestBody @Validated TradeStatisticsPlatformExchangeParam param) {
        log.info("getPlatformDailyReport param={}", JSONUtil.toJsonStr(param));
        return tradeApiService.getPlatformTradeStatistics(param);
    }

    /**
     * 导出 统计中心 平台日报表 （收款、出款）
     */
    @PostMapping("/exportPlatformDailyReport")
    public Mono<Result<String>> exportPlatformDailyReport(@RequestBody @Validated TradeStatisticsPlatformExchangeParam param) {
        log.info("exportPlatformDailyReport param={}", JSONUtil.toJsonStr(param));
        return tradeApiService.exportPlatformTradeStatistics(param);
    }

    /**
     * 统计中心 通道日报表 （收款、出款）
     */
    @PostMapping("/getChannelDailyReport")
    public Mono<PageResult<TradeChannelDailySnapchatExchangeDTO>> getChannelDailyReport(
            @RequestBody @Validated TradeStatisticsChannelExchangeParam param) {
        log.info("getChannelDailyReport param={}", JSONUtil.toJsonStr(param));
        return tradeApiService.getChannelTradeStatistics(param);
    }

    /**
     * 导出 统计中心 通道日报表 （收款、出款）
     */
    @PostMapping("/exportChannelDailyReport")
    public Mono<Result<String>> exportChannelDailyReport(@RequestBody @Validated TradeStatisticsChannelExchangeParam param) {
        log.info("exportChannelDailyReport param={}", JSONUtil.toJsonStr(param));
        return tradeApiService.exportChannelTradeStatistics(param);
    }

    /**
     * 统计中心 商户日报表 （收款、出款）
     */
    @PostMapping("/getMerchantDailyReport")
    public Mono<PageResult<TradeMerchantDailySnapchatExchangeDTO>> getMerchantDailyReport(
            @RequestBody @Validated TradeStatisticsMerchantExchangeParam param) {
        log.info("getMerchantDailyReport param={}", JSONUtil.toJsonStr(param));
        return tradeApiService.getMerchantTradeStatistics(param);
    }

    /**
     * 导出 统计中心 商户日报表 （收款、出款）
     */
    @PostMapping("/exportMerchantDailyReport")
    public Mono<Result<String>> exportMerchantDailyReport(@RequestBody @Validated TradeStatisticsMerchantExchangeParam param) {
        log.info("exportMerchantDailyReport param={}", JSONUtil.toJsonStr(param));
        return tradeApiService.exportMerchantTradeStatistics(param);
    }

    /**
     * 出款交易凭证
     */
    @PostMapping("/getPayoutReceipt")
    public Mono<Result<TradePayoutReceiptExchangeDTO>> getCashReceipt(@RequestBody TradeNoExchangeParam param) {
        log.info("getPayoutReceipt param={}", JSONUtil.toJsonStr(param));
        return tradeApiService.getPayoutReceipt(param);
    }

    /**
     * 分页查询充值订单
     */
    @PostMapping("/pageRechargeOrderList")
    public Mono<PageResult<TradeRechargeOrderPageExchangeDTO>> pageRechargeOrderList(@RequestBody TradeRechargeOrderPageExchangeParam param) {
        log.info("pageRechargeOrderList param={}", JSONUtil.toJsonStr(param));
        return tradeApiService.pageRechargeOrderList(param);
    }

    /**
     * 导出充值订单
     */
    @PostMapping("/exportRechargeOrderList")
    public Mono<Result<String>> exportRechargeOrderList(@RequestBody TradeRechargeOrderPageExchangeParam param) {
        log.info("exportRechargeOrderList param={}", JSONUtil.toJsonStr(param));
        return tradeApiService.exportRechargeOrderList(param);
    }

    /**
     * 人工发起充值审核
     */
    @OptLogFlag
    @GoogleAuthenticatorValid
    @PostMapping("/reviewRecharge")
    public Mono<Result<Boolean>> reviewRecharge(@RequestBody TradeAuthCodeReq param) {
        log.info("reviewRecharge param={}", JSONUtil.toJsonStr(param));
        return tradeApiService.reviewRecharge(param);
    }


    /**
     * 分页查询提现订单
     */
    @PostMapping("/pageWithdrawOrderList")
    public Mono<PageResult<TradeWithdrawOrderPageExchangeDTO>> pageWithdrawOrderList(@RequestBody TradeWithdrawOrderPageExchangeParam param) {
        log.info("pageWithdrawOrderList param={}", JSONUtil.toJsonStr(param));
        return tradeApiService.pageWithdrawOrderList(param);
    }


    /**
     * 导出提现订单
     */
    @PostMapping("/exportWithdrawOrderList")
    public Mono<Result<String>> exportWithdrawOrderList(@RequestBody TradeWithdrawOrderPageExchangeParam param) {
        log.info("exportWithdrawOrderList param={}", JSONUtil.toJsonStr(param));
        return tradeApiService.exportWithdrawOrderList(param);
    }


}
