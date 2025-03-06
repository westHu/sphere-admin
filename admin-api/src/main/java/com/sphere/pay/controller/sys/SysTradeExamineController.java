package com.sphere.pay.controller.sys;


import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sphere.pay.config.opt.OptLogFlag;
import com.sphere.pay.config.valid.GoogleAuthenticatorValid;
import com.sphere.pay.controller.request.IdReq;
import com.sphere.pay.controller.request.TradeExamineApplyReq;
import com.sphere.pay.controller.request.TradeExaminePageReq;
import com.sphere.pay.controller.request.TradeExamineReq;
import com.sphere.pay.controller.response.TradeExamineCountVO;
import com.sphere.pay.controller.response.TradeExamineVO;
import com.sphere.pay.convert.TradeConverter;
import com.sphere.pay.db.entity.SysExamineTrade;
import com.sphere.pay.param.TradeExaminePageParam;
import com.sphere.pay.param.TradeExamineParam;
import com.sphere.pay.result.PageResult;
import com.sphere.pay.result.Result;
import com.sphere.pay.service.sys.SysExamineTradeService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 账户申请API
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class SysTradeExamineController {

    @Resource
    SysExamineTradeService sysExamineTradeService;
    @Resource
    TradeConverter tradeConverter;


    /**
     * 统计待审核状态数量
     */
    @PostMapping("/countTradeExamine")
    public Mono<Result<List<TradeExamineCountVO>>> countTradeExamine() {
        log.info("countTradeExamine");
        List<SysExamineTrade> sysExamineTradeList = sysExamineTradeService.countTradeExamine();
        List<TradeExamineCountVO> voList = sysExamineTradeList.stream().map(e -> {
            TradeExamineCountVO vo = new TradeExamineCountVO();
            vo.setTradeType(e.getTradeType());
            vo.setToExamine(Math.toIntExact(e.getId()));
            return vo;
        }).collect(Collectors.toList());
        return Mono.just(Result.ok(voList));
    }

    /**
     * 分页查询交易（出款、提现、充值）待审核记录
     */
    @PostMapping("/pageTradeExamine")
    public Mono<PageResult<TradeExamineVO>> pageTradeExamine(@RequestBody @Validated TradeExaminePageReq req) {
        log.info("pageTradeExamine req={}", JSONUtil.toJsonStr(req));
        TradeExaminePageParam param = tradeConverter.convertTradeExaminePageParam(req);
        Page<SysExamineTrade> page = sysExamineTradeService.pageTradeExamine(param);
        List<TradeExamineVO> voList = tradeConverter.convertTradeExamineVOList(page.getRecords());
        return Mono.just(PageResult.ok(page.getTotal(), page.getCurrent(), voList));
    }

    /**
     * 查询明细
     */
    @PostMapping("/getTradeExamine")
    public Mono<Result<TradeExamineVO>> getTradeExamine(@RequestBody @Validated IdReq req) {
        log.info("getTradeExamine req={}", JSONUtil.toJsonStr(req));
        SysExamineTrade record = sysExamineTradeService.getTradeExamine(req.getId());
        TradeExamineVO vo = tradeConverter.convertTradeExamineDTO(record);
        return Mono.just(Result.ok(vo));
    }

    /**
     * 申领订单审核操作
     */
    @PostMapping("/applyTradeExamine")
    public Mono<Result<Void>> applyTradeExamine(@RequestBody @Validated TradeExamineApplyReq req, ServerWebExchange exchange) {
        log.info("applyTradeExamine req={}", JSONUtil.toJsonStr(req));
        sysExamineTradeService.applyTradeExamine(req.getTradeNo(), exchange);
        return Mono.just(Result.ok());
    }

    /**
     * 审核订单记录 记录操作日志
     */
    @OptLogFlag
    @GoogleAuthenticatorValid
    @PostMapping("/examineTrade")
    public Mono<Result<Void>> examineTrade(@RequestBody @Validated TradeExamineReq req, ServerWebExchange exchange) {
        log.info("examineTrade req={}", JSONUtil.toJsonStr(req));
        TradeExamineParam param = tradeConverter.convertTradeExamineParam(req);
        sysExamineTradeService.examineTrade(param, exchange);
        return Mono.just(Result.ok());
    }


}
