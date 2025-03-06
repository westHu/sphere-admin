package com.sphere.pay.convert;

import com.sphere.pay.controller.request.TradeExaminePageReq;
import com.sphere.pay.controller.request.TradeExamineReq;
import com.sphere.pay.controller.response.TradeExamineVO;
import com.sphere.pay.db.entity.SysExamineTrade;
import com.sphere.pay.param.TradeExaminePageParam;
import com.sphere.pay.param.TradeExamineParam;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface TradeConverter {

    TradeExaminePageParam convertTradeExaminePageParam(TradeExaminePageReq req);

    List<TradeExamineVO> convertTradeExamineVOList(List<SysExamineTrade> records);

    TradeExamineVO convertTradeExamineDTO(SysExamineTrade dto);

    TradeExamineParam convertTradeExamineParam(TradeExamineReq req);
}
