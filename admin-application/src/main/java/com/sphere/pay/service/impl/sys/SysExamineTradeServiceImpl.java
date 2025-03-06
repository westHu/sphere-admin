package com.sphere.pay.service.impl.sys;

import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sphere.pay.SysRequestManager;
import com.sphere.pay.db.entity.SysExamineTrade;
import com.sphere.pay.db.mapper.SysExamineTradeMapper;
import com.sphere.pay.dto.token.TokenUserDTO;
import com.sphere.pay.enums.ExamineStatusEnum;
import com.sphere.pay.exception.AdminException;
import com.sphere.pay.param.TradeExaminePageParam;
import com.sphere.pay.param.TradeExamineParam;
import com.sphere.pay.remote.trade.TradeApiService;
import com.sphere.pay.remote.trade.param.TradeReviewExchangeParam;
import com.sphere.pay.result.BaseResult;
import com.sphere.pay.service.sys.SysExamineTradeService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class SysExamineTradeServiceImpl extends ServiceImpl<SysExamineTradeMapper, SysExamineTrade>
        implements SysExamineTradeService {


    @Resource
    TradeApiService tradeApiService;


    @Override
    public List<SysExamineTrade> countTradeExamine() {
        QueryWrapper<SysExamineTrade> tradeExamineQuery = new QueryWrapper<>();
        tradeExamineQuery.select("trade_type as tradeType, count(1) as id");
        tradeExamineQuery.lambda().eq(SysExamineTrade::getExamineStatus, ExamineStatusEnum.TO_EXAMINE.getCode())
                .groupBy(SysExamineTrade::getTradeType);
        return this.list(tradeExamineQuery);
    }


    @Override
    public Page<SysExamineTrade> pageTradeExamine(TradeExaminePageParam param) {
        QueryWrapper<SysExamineTrade> tradeExamineQuery = new QueryWrapper<>();
        tradeExamineQuery.lambda()
                .eq(StringUtils.isNotBlank(param.getTradeNo()), SysExamineTrade::getTradeNo, param.getTradeNo())
                .eq(StringUtils.isNotBlank(param.getMerchantId()), SysExamineTrade::getMerchantId, param.getMerchantId())
                .eq(SysExamineTrade::getTradeType, param.getTradeType())
                .eq(Objects.nonNull(param.getStatus()), SysExamineTrade::getExamineStatus, param.getStatus())
                .orderByDesc(SysExamineTrade::getId);
        return this.page(new Page<>(param.getPageNum(), param.getPageSize()), tradeExamineQuery);
    }


    @Override
    public SysExamineTrade getTradeExamine(Long id) {
        QueryWrapper<SysExamineTrade> tradeExamineQuery = new QueryWrapper<>();
        tradeExamineQuery.lambda().eq(SysExamineTrade::getId, id);
        return this.getOne(tradeExamineQuery);
    }

    /**
     * FIXME 乐观锁
     */

    @Override
    public void applyTradeExamine(String tradeNo, ServerWebExchange exchange) {
        TokenUserDTO tokenUserDTO = SysRequestManager.getCurrentUser();
        if (Objects.isNull(tokenUserDTO)) {
            throw new AdminException("Can't parse current user");
        }
        String applyOperator = tokenUserDTO.getId() + ":" + tokenUserDTO.getUserName();

        UpdateWrapper<SysExamineTrade> tradeExamineUpdate = new UpdateWrapper<>();
        tradeExamineUpdate.lambda().set(SysExamineTrade::getApplyOperator, applyOperator)
                .set(SysExamineTrade::getExamineStatus, ExamineStatusEnum.EXAMINING.getCode())
                .set(SysExamineTrade::getApplyTime, LocalDateTime.now())
                .eq(SysExamineTrade::getTradeNo, tradeNo);
        this.update(tradeExamineUpdate);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void examineTrade(TradeExamineParam param, ServerWebExchange exchange) {
        TokenUserDTO tokenUserDTO = SysRequestManager.getCurrentUser();
        String examineOperator = tokenUserDTO.getUserName();
        Assert.notBlank(examineOperator, () -> new AdminException("Can't parse username"));

        QueryWrapper<SysExamineTrade> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysExamineTrade::getTradeNo, param.getTradeNo());
        SysExamineTrade sysExamineTrade = this.getOne(queryWrapper);
        if (Objects.isNull(sysExamineTrade)) {
            log.error("can't query order to examine. param={}", JSONUtil.toJsonStr(param));
            throw new AdminException("Can't query order to examine");
        }

        UpdateWrapper<SysExamineTrade> tradeExamineUpdate = new UpdateWrapper<>();
        tradeExamineUpdate.lambda().set(SysExamineTrade::getExamineOperator, examineOperator)
                .set(SysExamineTrade::getExamineStatus, param.getExamineStatus())
                .set(SysExamineTrade::getExamineTime, LocalDateTime.now())
                .set(SysExamineTrade::getExamineComment, param.getExamineComment())
                .eq(SysExamineTrade::getTradeNo, param.getTradeNo());
        this.update(tradeExamineUpdate);

        boolean reviewStatus = param.getExamineStatus().equals(ExamineStatusEnum.EXAMINE_PASS.getCode());
        TradeReviewExchangeParam reviewFeignParam = new TradeReviewExchangeParam();
        reviewFeignParam.setTradeNo(param.getTradeNo());
        reviewFeignParam.setReviewStatus(reviewStatus);
        reviewFeignParam.setReviewMsg(param.getExamineComment());
        BaseResult.parse(tradeApiService.tradeReview(reviewFeignParam).toFuture().join());
    }


}
