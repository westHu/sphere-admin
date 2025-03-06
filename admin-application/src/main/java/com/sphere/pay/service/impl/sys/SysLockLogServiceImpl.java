package com.sphere.pay.service.impl.sys;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sphere.pay.db.entity.SysLockLog;
import com.sphere.pay.db.mapper.SysLockLogMapper;
import com.sphere.pay.dto.SysLoginLogDTO;
import com.sphere.pay.param.SysLockLogParam;
import com.sphere.pay.result.PageResult;
import com.sphere.pay.service.sys.SysLockLogService;
import org.springframework.stereotype.Service;

/**
 * @author west
 */
@Service
public class SysLockLogServiceImpl extends ServiceImpl<SysLockLogMapper, SysLockLog>
        implements SysLockLogService {

    @Override
    public PageResult<SysLoginLogDTO> pageLockLogList(SysLockLogParam param) {
        return null;
    }
}
