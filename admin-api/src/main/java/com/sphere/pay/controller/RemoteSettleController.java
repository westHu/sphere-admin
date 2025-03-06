package com.sphere.pay.controller;


import cn.hutool.json.JSONUtil;
import com.sphere.pay.remote.settle.SettleApiService;
import com.sphere.pay.remote.settle.dto.AccountDropExchangeDTO;
import com.sphere.pay.remote.settle.dto.AccountExchangeDTO;
import com.sphere.pay.remote.settle.dto.AccountFlowExchangeDTO;
import com.sphere.pay.remote.settle.dto.AccountSnapshotSettlementExchangeDTO;
import com.sphere.pay.remote.settle.dto.AccountSnapshotSettlementGroupExchangeDTO;
import com.sphere.pay.remote.settle.dto.SettleOrderExchangeDTO;
import com.sphere.pay.remote.settle.param.AccountFlowPageExchangeParam;
import com.sphere.pay.remote.settle.param.AccountPageExchangeParam;
import com.sphere.pay.remote.settle.param.AccountSnapshotStatementExchangeParam;
import com.sphere.pay.remote.settle.param.AccountSnapshotStatementGroupExchangeParam;
import com.sphere.pay.remote.settle.param.SettleOrderPageExchangeParam;
import com.sphere.pay.result.PageResult;
import com.sphere.pay.result.Result;
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
 * 清结算资金API
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class RemoteSettleController {

    @Resource
    SettleApiService settleApiService;

    /**
     * 查询账户（下拉框）
     */
    @PostMapping("/dropAccountList")
    public Mono<Result<List<AccountDropExchangeDTO>>> dropAccountList() {
        return settleApiService.dropAccountList();
    }

    /**
     * 分页查询账户信息
     */
    @PostMapping("/pageAccountList")
    public Mono<PageResult<AccountExchangeDTO>> pageAccountList(@RequestBody @Validated AccountPageExchangeParam param) {
        log.info("Settle pageAccountList param={}", JSONUtil.toJsonStr(param));
        return settleApiService.pageAccountList(param);
    }

    /**
     * 分页查询账户流水信息
     */
    @PostMapping("/pageAccountFlowList")
    public Mono<PageResult<AccountFlowExchangeDTO>> pageAccountFlowList(@RequestBody @Validated AccountFlowPageExchangeParam param) {
        log.info("Settle pageAccountFlowList param={}", JSONUtil.toJsonStr(param));
        return settleApiService.pageAccountFlowList(param);
    }


    /**
     * 查询余额快照对账单
     */
    @PostMapping("/groupAccountSnapshotStatement")
    public Mono<PageResult<AccountSnapshotSettlementGroupExchangeDTO>> groupAccountSnapshotStatement(@RequestBody @Validated AccountSnapshotStatementGroupExchangeParam param) {
        log.info("Settle groupAccountSnapshotStatement param={}", JSONUtil.toJsonStr(param));
        return settleApiService.groupAccountSnapshotStatement(param);
    }

    /**
     * 查询余额快照对账单详情
     */
    @PostMapping("/pageAccountSnapshotStatement")
    public Mono<PageResult<AccountSnapshotSettlementExchangeDTO>> pageAccountSnapshotStatement(@RequestBody @Validated AccountSnapshotStatementExchangeParam param) {
        log.info("Settle pageAccountSnapshotStatement param={}", JSONUtil.toJsonStr(param));
        return settleApiService.pageAccountSnapshotStatement(param);
    }

    /**
     * 分页查询结算订单
     */
    @PostMapping("/pageSettleOrderList")
    public Mono<PageResult<SettleOrderExchangeDTO>> pageSettleOrderList(@RequestBody @Validated SettleOrderPageExchangeParam param) {
        log.info("Settle pageAccountSnapshotStatement param={}", JSONUtil.toJsonStr(param));
        return settleApiService.pageSettleOrderList(param);
    }


}
