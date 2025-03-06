package com.sphere.pay.remote.settle;

import com.sphere.pay.remote.settle.dto.AccountDropExchangeDTO;
import com.sphere.pay.remote.settle.dto.AccountExchangeDTO;
import com.sphere.pay.remote.settle.dto.AccountFlowExchangeDTO;
import com.sphere.pay.remote.settle.dto.AccountSnapshotSettlementExchangeDTO;
import com.sphere.pay.remote.settle.dto.AccountSnapshotSettlementGroupExchangeDTO;
import com.sphere.pay.remote.settle.dto.SettleOrderExchangeDTO;
import com.sphere.pay.remote.settle.dto.SettleTimelyStatisticsIndexExchangeDTO;
import com.sphere.pay.remote.settle.param.AccountFlowPageExchangeParam;
import com.sphere.pay.remote.settle.param.AccountPageExchangeParam;
import com.sphere.pay.remote.settle.param.AccountSnapshotStatementExchangeParam;
import com.sphere.pay.remote.settle.param.AccountSnapshotStatementGroupExchangeParam;
import com.sphere.pay.remote.settle.param.SettleOrderPageExchangeParam;
import com.sphere.pay.remote.settle.param.SettleTimelyStatisticsIndexExchangeParam;
import com.sphere.pay.result.PageResult;
import com.sphere.pay.result.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@HttpExchange
public interface SettleApiService {



    //----- 账户 ------

    /**
     * 查询账户（下拉框）
     */
    @PostExchange("/dropAccountList")
    Mono<Result<List<AccountDropExchangeDTO>>> dropAccountList();

    /**
     * 分页查询账户列表
     */
    @PostExchange("/pageAccountList")
    Mono<PageResult<AccountExchangeDTO>> pageAccountList(@RequestBody AccountPageExchangeParam param);




    //----- 账户流水 ------


    /**
     * 分页查询账户流水列表
     */
    @PostExchange("/pageAccountFlowList")
    Mono<PageResult<AccountFlowExchangeDTO>> pageAccountFlowList(@RequestBody AccountFlowPageExchangeParam param);



    //----- 结算单 ------

    /**
     * 分页查询结算订单
     */
    @PostExchange("/pageSettleOrderList")
    Mono<PageResult<SettleOrderExchangeDTO>> pageSettleOrderList(@RequestBody SettleOrderPageExchangeParam param);



    //----- 账户快照 ------

    /**
     * 查询余额快照对账单
     */
    @PostExchange("/groupAccountSnapshotStatement")
    Mono<PageResult<AccountSnapshotSettlementGroupExchangeDTO>> groupAccountSnapshotStatement(
            @RequestBody AccountSnapshotStatementGroupExchangeParam param);

    /**
     * 查询余额快照对账单详情
     */
    @PostExchange("/pageAccountSnapshotStatement")
    Mono<PageResult<AccountSnapshotSettlementExchangeDTO>> pageAccountSnapshotStatement(
            @RequestBody AccountSnapshotStatementExchangeParam param);



    //----- 其他 ------

    /**
     * 首页，清结算信息
     */
    @PostExchange("/getSettleTimelyStatistics4Index")
    Mono<Result<SettleTimelyStatisticsIndexExchangeDTO>> getSettleTimelyStatistics4Index(
            @RequestBody SettleTimelyStatisticsIndexExchangeParam param);



}
