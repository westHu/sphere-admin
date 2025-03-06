package com.sphere.pay.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sphere.pay.db.entity.SysLoginLog;
import com.sphere.pay.dto.SysLoginLogDTO;
import com.sphere.pay.param.LoginLogParam;
import com.sphere.pay.result.PageResult;

/**
 * @author west
 */
public interface SysLoginLogService extends IService<SysLoginLog> {

    PageResult<SysLoginLogDTO> pageLoginLogList(LoginLogParam param);
}
