package com.sphere.pay.service.impl.sys;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sphere.pay.db.entity.SysExamineTrade;
import com.sphere.pay.dto.dashboard.ChannelProportionDTO;
import com.sphere.pay.dto.dashboard.DashboardChannelDTO;
import com.sphere.pay.dto.dashboard.DashboardDTO;
import com.sphere.pay.dto.dashboard.DashboardGeneralDTO;
import com.sphere.pay.dto.dashboard.DashboardPaymentDTO;
import com.sphere.pay.dto.dashboard.DashboardPlatformDTO;
import com.sphere.pay.dto.dashboard.DashboardSettleDTO;
import com.sphere.pay.dto.dashboard.DashboardTradeDTO;
import com.sphere.pay.dto.dashboard.DashboardTradeTrendDTO;
import com.sphere.pay.dto.dashboard.DashboardWorkbenchesDTO;
import com.sphere.pay.dto.dashboard.PaymentProportionDTO;
import com.sphere.pay.dto.dashboard.PaymentSuccessProportionDTO;
import com.sphere.pay.dto.dashboard.PaymentSuccessRankingDTO;
import com.sphere.pay.dto.dashboard.PlatformMerchantCountDTO;
import com.sphere.pay.dto.dashboard.PlatformMerchantFundDTO;
import com.sphere.pay.dto.dashboard.PlatformSelfFundDTO;
import com.sphere.pay.dto.dashboard.TradeTrendDTO;
import com.sphere.pay.enums.ExamineStatusEnum;
import com.sphere.pay.remote.merchant.MerchantApiService;
import com.sphere.pay.remote.merchant.dto.MerchantTimelyStatisticsIndexExchangeDTO;
import com.sphere.pay.remote.payment.PaymentApiService;
import com.sphere.pay.remote.payment.dto.PaymentTimelyStatisticsChannelIndexFeignDTO;
import com.sphere.pay.remote.payment.dto.PaymentTimelyStatisticsIndexFeignDTO;
import com.sphere.pay.remote.payment.dto.PaymentTimelyStatisticsProportionIndexFeignDTO;
import com.sphere.pay.remote.payment.dto.PaymentTimelyStatisticsRankingIndexFeignDTO;
import com.sphere.pay.remote.payment.param.PaymentTimelyStatisticsIndexFeignParam;
import com.sphere.pay.remote.settle.SettleApiService;
import com.sphere.pay.remote.settle.dto.SettleTimelyStatisticsIndexExchangeDTO;
import com.sphere.pay.remote.settle.param.SettleTimelyStatisticsIndexExchangeParam;
import com.sphere.pay.remote.trade.TradeApiService;
import com.sphere.pay.remote.trade.dto.TradeTimelyStatisticsIndexExchangeDTO;
import com.sphere.pay.remote.trade.dto.TradeTimelyStatisticsIndexSnapshotFeignDTO;
import com.sphere.pay.remote.trade.param.TradeTimelyStatisticsIndexExchangeParam;
import com.sphere.pay.result.BaseResult;
import com.sphere.pay.service.sys.SysDashboardService;
import com.sphere.pay.service.sys.SysExamineTradeService;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SysDashboardServiceImpl implements SysDashboardService {

    @Resource
    TradeApiService tradeApiService;
    @Resource
    PaymentApiService paymentApiService;
    @Resource
    SettleApiService settleApiService;
    @Resource
    MerchantApiService merchantApiService;

    @Resource
    SysExamineTradeService sysExamineTradeService;

    //@CacheFlag(time = 60 * 5, target = DashboardDTO.class)
    @SneakyThrows
    @Override
    public DashboardDTO sysDashboard() {
        DashboardDTO dashboardDTO = new DashboardDTO();

        String today = LocalDate.now().toString();
        String startTime = today + " 00:00:00";
        String endTime = today + " 23:59:59";
        log.info("sysDashboard startTime={}, endTime={}", startTime, endTime);

        //查询交易
        TradeTimelyStatisticsIndexExchangeParam tradeParam = new TradeTimelyStatisticsIndexExchangeParam();
        tradeParam.setStartTime(startTime);
        tradeParam.setEndTime(endTime);
        TradeTimelyStatisticsIndexExchangeDTO tradeDTO = BaseResult.parse(tradeApiService.getTradeTimelyStatistics4Index(tradeParam).toFuture().join());

        //查询渠道
        PaymentTimelyStatisticsIndexFeignParam paymentParam = new PaymentTimelyStatisticsIndexFeignParam();
        paymentParam.setStartTime(startTime);
        paymentParam.setEndTime(endTime);
        PaymentTimelyStatisticsIndexFeignDTO paymentDTO = BaseResult.parse(paymentApiService.getPaymentTimelyStatistics4Index(paymentParam).toFuture().join());

        //查询清结算
        SettleTimelyStatisticsIndexExchangeParam settleParam = new SettleTimelyStatisticsIndexExchangeParam();
        settleParam.setStartTime(startTime);
        settleParam.setEndTime(endTime);
        SettleTimelyStatisticsIndexExchangeDTO settleDTO = BaseResult.parse(settleApiService.getSettleTimelyStatistics4Index(settleParam).toFuture().join());

        //查询商户
        MerchantTimelyStatisticsIndexExchangeDTO merchantDTO = BaseResult.parse(merchantApiService.getMerchantTimelyStatistics4Index().toFuture().join());
        log.info("sysDashboard tradeDTO={}", JSONUtil.toJsonStr(tradeDTO));
        log.info("sysDashboard paymentDTO={}", JSONUtil.toJsonStr(paymentDTO));
        log.info("sysDashboard settleDTO={}", JSONUtil.toJsonStr(settleDTO));
        log.info("sysDashboard merchantDTO={}", JSONUtil.toJsonStr(merchantDTO));
        
        /**
         * 工作台
         */
        DashboardWorkbenchesDTO dashboardWorkbenchesDTO = getDashboardWorkbenchesDTO();
        dashboardDTO.setWorkbenches(dashboardWorkbenchesDTO);

        /**
         * 概述数据
         */
        DashboardGeneralDTO dashboardGeneralDTO = new DashboardGeneralDTO();
        dashboardGeneralDTO.setTradeAmount(tradeDTO.getPaymentAmount().add(tradeDTO.getPayoutAmount()));
        dashboardGeneralDTO.setTradeSuccessAmount(tradeDTO.getPaymentSuccessAmount().add(tradeDTO.getPayoutSuccessAmount()));
        dashboardGeneralDTO.setMerchantCount(merchantDTO.getMerchantCount());
        dashboardGeneralDTO.setPlatformProfitAmount(settleDTO.getPaymentPlatformProfit().add(settleDTO.getPayoutPlatformProfit()));
        dashboardDTO.setGeneral(dashboardGeneralDTO);

        /**
         * 交易数据
         */
        DashboardTradeDTO dashboardTradeDTO = new DashboardTradeDTO();
        dashboardTradeDTO.setPaymentCount(tradeDTO.getPaymentCount());
        dashboardTradeDTO.setPaymentAmount(tradeDTO.getPaymentAmount());
        dashboardTradeDTO.setPaymentSuccessCount(tradeDTO.getPaymentSuccessCount());
        dashboardTradeDTO.setPaymentSuccessAmount(tradeDTO.getPaymentSuccessAmount());
        dashboardTradeDTO.setPaymentSuccessRate(tradeDTO.getPaymentSuccessRate());

        dashboardTradeDTO.setPayoutCount(tradeDTO.getPayoutCount());
        dashboardTradeDTO.setPayoutAmount(tradeDTO.getPayoutAmount());
        dashboardTradeDTO.setPayoutSuccessCount(tradeDTO.getPayoutSuccessCount());
        dashboardTradeDTO.setPayoutSuccessAmount(tradeDTO.getPayoutSuccessAmount());
        dashboardTradeDTO.setPayoutSuccessRate(tradeDTO.getPayoutSuccessRate());
        dashboardDTO.setTrade(dashboardTradeDTO);

        /**
         * 趋势数据
         */
        List<TradeTimelyStatisticsIndexSnapshotFeignDTO> snapshotTradeStatisticsList =
                tradeDTO.getSnapshotTradeStatisticsList();
        List<TradeTrendDTO> collect = snapshotTradeStatisticsList.stream().map(e -> {
            TradeTrendDTO tradeTrendDTO = new TradeTrendDTO();
            tradeTrendDTO.setDate(e.getTradeDate());
            tradeTrendDTO.setAmount(Objects.isNull(e.getAmount()) ? BigDecimal.ZERO : e.getAmount());
            return tradeTrendDTO;
        }).collect(Collectors.toList());
        DashboardTradeTrendDTO tradeTrendDTO = new DashboardTradeTrendDTO();
        tradeTrendDTO.setTradeTrendList(collect);
        dashboardDTO.setTrend(tradeTrendDTO);

        /**
         * 渠道支付方式数据
         */
        List<PaymentTimelyStatisticsProportionIndexFeignDTO> transactionPaymentProportionList =
                paymentDTO.getTransactionPaymentProportionList();
        List<PaymentProportionDTO> paymentProportionDTOList = transactionPaymentProportionList.stream().map(e -> {
            PaymentProportionDTO proportionDTO = new PaymentProportionDTO();
            proportionDTO.setPayment(e.getPaymentMethod());
            proportionDTO.setAmount(Objects.isNull(e.getAmount()) ? BigDecimal.ZERO : e.getAmount());
            return proportionDTO;
        }).collect(Collectors.toList());

        List<PaymentSuccessProportionDTO> paymentSuccessProportionDTOList =
                transactionPaymentProportionList.stream().map(e -> {
                    PaymentSuccessProportionDTO proportionDTO = new PaymentSuccessProportionDTO();
                    proportionDTO.setPayment(e.getPaymentMethod());
                    proportionDTO.setAmount(Objects.isNull(e.getAmount()) ? BigDecimal.ZERO : e.getAmount());
                    return proportionDTO;
                }).collect(Collectors.toList());

        List<PaymentTimelyStatisticsRankingIndexFeignDTO> transactionPaymentRankingList =
                paymentDTO.getTransactionPaymentRankingList();
        List<PaymentSuccessRankingDTO> paymentSuccessRankingDTOList = transactionPaymentRankingList.stream().map(e -> {
            PaymentSuccessRankingDTO rankingDTO = new PaymentSuccessRankingDTO();
            rankingDTO.setPayment(e.getPaymentMethod());
            rankingDTO.setSuccessRate(Objects.isNull(e.getSuccessRate()) ? BigDecimal.ZERO : e.getSuccessRate());
            return rankingDTO;
        }).collect(Collectors.toList());

        List<PaymentTimelyStatisticsProportionIndexFeignDTO> disbursementProportionList =
                paymentDTO.getDisbursementProportionList();
        List<PaymentProportionDTO> paymentProportionDTOList1 = disbursementProportionList.stream().map(e -> {
            PaymentProportionDTO proportionDTO = new PaymentProportionDTO();
            proportionDTO.setPayment(e.getPaymentMethod());
            proportionDTO.setAmount(Objects.isNull(e.getAmount()) ? BigDecimal.ZERO : e.getAmount());
            return proportionDTO;
        }).collect(Collectors.toList());

        List<PaymentSuccessProportionDTO> paymentSuccessProportionDTOList1 =
                disbursementProportionList.stream().map(e -> {
                    PaymentSuccessProportionDTO proportionDTO = new PaymentSuccessProportionDTO();
                    proportionDTO.setPayment(e.getPaymentMethod());
                    proportionDTO.setAmount(Objects.isNull(e.getAmount()) ? BigDecimal.ZERO : e.getAmount());
                    return proportionDTO;
                }).collect(Collectors.toList());

        List<PaymentTimelyStatisticsRankingIndexFeignDTO> disbursementPaymentRankingList =
                paymentDTO.getDisbursementPaymentRankingList();
        List<PaymentSuccessRankingDTO> paymentSuccessRankingDTOList1 =
                disbursementPaymentRankingList.stream().map(e -> {
                    PaymentSuccessRankingDTO rankingDTO = new PaymentSuccessRankingDTO();
                    rankingDTO.setPayment(e.getPaymentMethod());
                    rankingDTO.setSuccessRate(Objects.isNull(e.getSuccessRate()) ? BigDecimal.ZERO :
                            e.getSuccessRate());
                    return rankingDTO;
                }).collect(Collectors.toList());

        DashboardPaymentDTO dashboardPaymentDTO = new DashboardPaymentDTO();
        dashboardPaymentDTO.setPaymentProportionList(paymentProportionDTOList);
        dashboardPaymentDTO.setPaymentSuccessProportionList(paymentSuccessProportionDTOList);
        dashboardPaymentDTO.setPaymentSuccessRankingList(paymentSuccessRankingDTOList);
        dashboardPaymentDTO.setPayoutProportionList(paymentProportionDTOList1);
        dashboardPaymentDTO.setPayoutSuccessProportionList(paymentSuccessProportionDTOList1);
        dashboardPaymentDTO.setPayoutSuccessRankingList(paymentSuccessRankingDTOList1);
        dashboardDTO.setPayment(dashboardPaymentDTO);

        /**
         * 清结算数据
         */
        DashboardSettleDTO dashboardSettleDTO = new DashboardSettleDTO();
        dashboardSettleDTO.setPaymentMerchantFee(settleDTO.getPaymentMerchantFee());
        dashboardSettleDTO.setPaymentMerchantProfit(settleDTO.getPaymentMerchantProfit());
        dashboardSettleDTO.setPaymentChannelCost(settleDTO.getPaymentChannelCost());
        dashboardSettleDTO.setPaymentPlatformProfit(settleDTO.getPaymentPlatformProfit());
        dashboardSettleDTO.setPayoutMerchantFee(settleDTO.getPayoutMerchantFee());
        dashboardSettleDTO.setPayoutMerchantProfit(settleDTO.getPayoutMerchantProfit());
        dashboardSettleDTO.setPayoutChannelCost(settleDTO.getPayoutChannelCost());
        dashboardSettleDTO.setPayoutPlatformProfit(settleDTO.getPayoutPlatformProfit());
        dashboardDTO.setSettle(dashboardSettleDTO);

        /**
         * 平台信息
         */
        PlatformMerchantCountDTO platformMerchantCount = new PlatformMerchantCountDTO();
        platformMerchantCount.setMerchantCount(merchantDTO.getMerchantCount());
        platformMerchantCount.setMerchantCountAddInYesterday(merchantDTO.getMerchantCount1Add());
        platformMerchantCount.setMerchantCountAddIn7Day(merchantDTO.getMerchantCount7Add());

        PlatformMerchantFundDTO platformMerchantFund = new PlatformMerchantFundDTO();
        platformMerchantFund.setMerchantSumAmount(settleDTO.getMerchantAmount());
        platformMerchantFund.setMerchantSumAmountInYesterday(settleDTO.getMerchantAmount1Add());
        platformMerchantFund.setMerchantSumAmountAddIn7Day(settleDTO.getMerchantAmount7Add());

        PlatformSelfFundDTO platformSelfFund = new PlatformSelfFundDTO();
        platformSelfFund.setPlatformSumAmount(settleDTO.getPlatformAmount());
        platformSelfFund.setPlatformSumAmountInYesterday(settleDTO.getPlatformAmount1Add());
        platformSelfFund.setPlatformSumAmountAddIn7Day(settleDTO.getPlatformAmount7Add());

        DashboardPlatformDTO dashboardPlatformDTO = new DashboardPlatformDTO();
        dashboardPlatformDTO.setPlatformMerchantCount(platformMerchantCount);
        dashboardPlatformDTO.setPlatformMerchantFund(platformMerchantFund);
        dashboardPlatformDTO.setPlatformSelfFund(platformSelfFund);
        dashboardDTO.setPlatform(dashboardPlatformDTO);

        /**
         * 渠道信息
         */
        List<PaymentTimelyStatisticsChannelIndexFeignDTO> channelProportionList = paymentDTO.getChannelProportionList();
        List<ChannelProportionDTO> channelProportionDTOList = channelProportionList.stream().map(e -> {
            ChannelProportionDTO proportionDTO = new ChannelProportionDTO();
            proportionDTO.setChannel(e.getChannelName());
            proportionDTO.setAmount(Objects.isNull(e.getAmount()) ? BigDecimal.ZERO : e.getAmount());
            return proportionDTO;
        }).collect(Collectors.toList());

        DashboardChannelDTO dashboardChannelDTO = new DashboardChannelDTO();
        dashboardChannelDTO.setChannelProportionList(channelProportionDTOList);
        dashboardDTO.setChannel(dashboardChannelDTO);

        return dashboardDTO;
    }


    //=========================

    /**
     * 工作台
     */
    private DashboardWorkbenchesDTO getDashboardWorkbenchesDTO() {
        QueryWrapper<SysExamineTrade> tradeQuery = new QueryWrapper<>();
        tradeQuery.lambda().eq(SysExamineTrade::getExamineStatus, ExamineStatusEnum.TO_EXAMINE.getCode());
        long tradePending = sysExamineTradeService.count(tradeQuery);

        DashboardWorkbenchesDTO workbenchesDTO = new DashboardWorkbenchesDTO();
        workbenchesDTO.setTradePending(tradePending);
        return workbenchesDTO;
    }

}
