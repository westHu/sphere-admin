package com.sphere.pay.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sphere.pay.db.entity.SysOptLog;
import com.sphere.pay.dto.SysLoginLogDTO;
import com.sphere.pay.param.SysOptLogParam;
import com.sphere.pay.result.PageResult;

/**
 * @author west
 */
public interface SysOptLogService extends IService<SysOptLog> {

    PageResult<SysLoginLogDTO> pageOptLogList(SysOptLogParam param);

}
