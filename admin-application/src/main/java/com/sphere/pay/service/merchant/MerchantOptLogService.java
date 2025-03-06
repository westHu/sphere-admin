package com.sphere.pay.service.merchant;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sphere.pay.db.entity.MerchantOptLog;
import com.sphere.pay.dto.MerchantOptLogDTO;
import com.sphere.pay.param.MerchantOptLogPageParam;
import com.sphere.pay.result.PageResult;

public interface MerchantOptLogService extends IService<MerchantOptLog> {

    PageResult<MerchantOptLogDTO> pageMerchantOptLogList(MerchantOptLogPageParam param);
}
