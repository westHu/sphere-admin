package com.sphere.pay.service.impl.sys;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sphere.pay.db.entity.SysOptLog;
import com.sphere.pay.db.mapper.SysOptLogMapper;
import com.sphere.pay.dto.SysLoginLogDTO;
import com.sphere.pay.param.SysOptLogParam;
import com.sphere.pay.result.PageResult;
import com.sphere.pay.service.sys.SysOptLogService;
import org.springframework.stereotype.Service;

/**
 * @author west
 */
@Service
public class SysOptLogServiceImpl extends ServiceImpl<SysOptLogMapper, SysOptLog>
        implements SysOptLogService {

    @Override
    public PageResult<SysLoginLogDTO> pageOptLogList(SysOptLogParam param) {
        return null;
    }
}
