package com.sphere.pay.service.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sphere.pay.db.entity.SysExamineTrade;
import com.sphere.pay.param.TradeExaminePageParam;
import com.sphere.pay.param.TradeExamineParam;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

public interface SysExamineTradeService extends IService<SysExamineTrade> {

    List<SysExamineTrade> countTradeExamine();

    Page<SysExamineTrade> pageTradeExamine(TradeExaminePageParam param);

    SysExamineTrade getTradeExamine(Long id);

    void applyTradeExamine(String tradeNo, ServerWebExchange exchange);

    void examineTrade(TradeExamineParam param, ServerWebExchange exchange);

}
