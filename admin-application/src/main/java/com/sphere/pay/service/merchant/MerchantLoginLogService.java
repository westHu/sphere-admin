package com.sphere.pay.service.merchant;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sphere.pay.db.entity.MerchantLoginLog;
import com.sphere.pay.dto.MerchantLoginLogDTO;
import com.sphere.pay.param.MerchantLoginLogPageParam;
import com.sphere.pay.result.PageResult;

public interface MerchantLoginLogService extends IService<MerchantLoginLog> {

    PageResult<MerchantLoginLogDTO> pageMerchantLoginLogList(MerchantLoginLogPageParam param);
}
