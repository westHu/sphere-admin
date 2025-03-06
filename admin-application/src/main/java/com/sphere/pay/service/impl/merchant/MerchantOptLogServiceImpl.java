package com.sphere.pay.service.impl.merchant;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sphere.pay.db.entity.MerchantOptLog;
import com.sphere.pay.db.mapper.MerchantOptLogMapper;
import com.sphere.pay.dto.MerchantOptLogDTO;
import com.sphere.pay.param.MerchantOptLogPageParam;
import com.sphere.pay.result.PageResult;
import com.sphere.pay.service.merchant.MerchantOptLogService;
import org.springframework.stereotype.Service;

@Service
public class MerchantOptLogServiceImpl extends ServiceImpl<MerchantOptLogMapper, MerchantOptLog>
        implements MerchantOptLogService {

    @Override
    public PageResult<MerchantOptLogDTO> pageMerchantOptLogList(MerchantOptLogPageParam param) {
        return null;
    }
}
