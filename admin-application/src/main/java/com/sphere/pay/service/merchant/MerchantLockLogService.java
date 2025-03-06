package com.sphere.pay.service.merchant;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sphere.pay.db.entity.MerchantLockLog;
import com.sphere.pay.dto.MerchantLockLogDTO;
import com.sphere.pay.param.MerchantLockLogPageParam;
import com.sphere.pay.param.MerchantLockParam;
import com.sphere.pay.param.MerchantUnlockParam;
import com.sphere.pay.result.PageResult;

public interface MerchantLockLogService extends IService<MerchantLockLog> {

    PageResult<MerchantLockLogDTO> pageMerchantLockLogList(MerchantLockLogPageParam param);

    MerchantLockLogDTO getMerchantLockLog(MerchantLockParam param);

    boolean unLock4MerchantLogin(MerchantUnlockParam param);

}
