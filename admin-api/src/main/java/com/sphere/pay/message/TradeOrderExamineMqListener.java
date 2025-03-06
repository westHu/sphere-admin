package com.sphere.pay.message;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.sphere.pay.db.entity.SysExamineTrade;
import com.sphere.pay.enums.ExamineStatusEnum;
import com.sphere.pay.enums.TradeTypeEnum;
import com.sphere.pay.exception.AdminException;
import com.sphere.pay.message.dto.TradeCashExamineMessageDTO;
import com.sphere.pay.message.dto.TradeRechargeExamineMessageDTO;
import com.sphere.pay.message.dto.TradeTransferExamineMessageDTO;
import com.sphere.pay.message.dto.TradeWithdrawExamineMessageDTO;
import com.sphere.pay.service.sys.SysExamineTradeService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;


/**
 * 交易订单（出款、充值、提现、转账）审核
 */
@Slf4j
@Component
public class TradeOrderExamineMqListener  {


    @Resource
    SysExamineTradeService sysExamineTradeService;


    public void onMessage(String message) {
        log.info("trade examine message = {}", message);
        try {
            JSONObject parseObj = JSONUtil.parseObj(message);
            String tradeNo = parseObj.getStr("tradeNo");
            TradeTypeEnum tradeTypeEnum = TradeTypeEnum.tradeNoToTradeType(tradeNo);
            log.info("tradeOrderExamineMqListener tradeTypeEnum={}", tradeTypeEnum.name());
            SysExamineTrade sysExamineTrade = switch (tradeTypeEnum) {
                case CASH -> doExamine4Payout(message);
                case TRANSFER -> doExamine4Transfer(message);
                case RECHARGE -> doExamine4Recharge(message);
                case WITHDRAW -> doExamine4Withdraw(message);
                default -> throw new AdminException("unknown trade type");
            };
            //发送消息
            //todo
        } catch (Exception e) {
            log.error("mq message trade examine exception: ", e);
            throw new AdminException("trade examine exception");
        }
    }

    /**
     * 提现审核
     */
    private SysExamineTrade doExamine4Withdraw(String message) {
        TradeWithdrawExamineMessageDTO dto = JSONUtil.toBean(message, TradeWithdrawExamineMessageDTO.class);

        if (Objects.isNull(dto)) {
            log.info("withdraw Object is required");
            return null;
        }

        SysExamineTrade sysExamineTrade = new SysExamineTrade();
        sysExamineTrade.setTradeNo(dto.getTradeNo());
        sysExamineTrade.setTradeType(TradeTypeEnum.WITHDRAW.getCode());
        sysExamineTrade.setMerchantId(dto.getMerchantId());
        sysExamineTrade.setMerchantName(dto.getMerchantName());
        sysExamineTrade.setExamineMessage(message);
        sysExamineTrade.setApplyTime(LocalDateTime.now());
        sysExamineTrade.setApplyOperator(dto.getMerchantName());
        sysExamineTrade.setCreateTime(LocalDateTime.now());
        sysExamineTrade.setExamineStatus(ExamineStatusEnum.TO_EXAMINE.getCode());
        sysExamineTradeService.save(sysExamineTrade);
        return sysExamineTrade;
    }

    /**
     * 充值审核
     */
    private SysExamineTrade doExamine4Recharge(String message) {
        TradeRechargeExamineMessageDTO dto = JSONUtil.toBean(message, TradeRechargeExamineMessageDTO.class);

        if (Objects.isNull(dto)) {
            log.info("recharge Object is required");
            return null;
        }

        SysExamineTrade sysExamineTrade = new SysExamineTrade();
        sysExamineTrade.setTradeNo(dto.getTradeNo());
        sysExamineTrade.setTradeType(TradeTypeEnum.RECHARGE.getCode());
        sysExamineTrade.setMerchantId(dto.getMerchantId());
        sysExamineTrade.setMerchantName(dto.getMerchantName());
        sysExamineTrade.setExamineMessage(message);
        sysExamineTrade.setApplyTime(LocalDateTime.now());
        sysExamineTrade.setApplyOperator(dto.getMerchantName());
        sysExamineTrade.setCreateTime(LocalDateTime.now());
        sysExamineTrade.setExamineStatus(ExamineStatusEnum.TO_EXAMINE.getCode());
        sysExamineTradeService.save(sysExamineTrade);
        return sysExamineTrade;
    }

    /**
     * 转账审核
     */
    private SysExamineTrade doExamine4Transfer(String message) {
        TradeTransferExamineMessageDTO dto = JSONUtil.toBean(message, TradeTransferExamineMessageDTO.class);

        if (Objects.isNull(dto)) {
            log.info("transfer Object is required");
            return null;
        }

        SysExamineTrade sysExamineTrade = new SysExamineTrade();
        sysExamineTrade.setTradeNo(dto.getTradeNo());
        sysExamineTrade.setTradeType(TradeTypeEnum.TRANSFER.getCode());
        sysExamineTrade.setMerchantId(dto.getTransferOutMerchantId());
        sysExamineTrade.setMerchantName(dto.getTransferOutMerchantName());
        sysExamineTrade.setExamineMessage(message);
        sysExamineTrade.setApplyTime(LocalDateTime.now());
        sysExamineTrade.setApplyOperator(dto.getApplyOperator());
        sysExamineTrade.setCreateTime(LocalDateTime.now());
        sysExamineTrade.setExamineStatus(ExamineStatusEnum.TO_EXAMINE.getCode());
        sysExamineTradeService.save(sysExamineTrade);
        return sysExamineTrade;
    }

    /**
     * 出款审核
     */
    private SysExamineTrade doExamine4Payout(String message) {
        TradeCashExamineMessageDTO dto = JSONUtil.toBean(message, TradeCashExamineMessageDTO.class);

        if (Objects.isNull(dto)) {
            log.info("doExamine4Payout Object is required");
            return null;
        }

        SysExamineTrade sysExamineTrade = new SysExamineTrade();
        sysExamineTrade.setTradeNo(dto.getTradeNo());
        sysExamineTrade.setTradeType(TradeTypeEnum.CASH.getCode());
        sysExamineTrade.setMerchantId(dto.getMerchantId());
        sysExamineTrade.setMerchantName(dto.getMerchantName());
        sysExamineTrade.setExamineMessage(message);
        sysExamineTrade.setApplyTime(LocalDateTime.now());
        sysExamineTrade.setApplyOperator(dto.getMerchantName());
        sysExamineTrade.setCreateTime(LocalDateTime.now());
        sysExamineTrade.setExamineStatus(ExamineStatusEnum.TO_EXAMINE.getCode());
        sysExamineTradeService.save(sysExamineTrade);
        return sysExamineTrade;
    }
}