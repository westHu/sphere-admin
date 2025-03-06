package com.sphere.pay.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sphere.pay.db.entity.SysLockLog;
import com.sphere.pay.dto.SysLoginLogDTO;
import com.sphere.pay.param.SysLockLogParam;
import com.sphere.pay.result.PageResult;

/**
 * @author west
 */
public interface SysLockLogService extends IService<SysLockLog> {
    PageResult<SysLoginLogDTO> pageLockLogList(SysLockLogParam param);
}
