package com.sphere.pay.service.impl.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sphere.pay.db.entity.SysLoginLog;
import com.sphere.pay.db.mapper.SysLoginLogMapper;
import com.sphere.pay.dto.SysLoginLogDTO;
import com.sphere.pay.param.LoginLogParam;
import com.sphere.pay.result.PageResult;
import com.sphere.pay.service.sys.SysLoginLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author west
 */
@Service
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog>
        implements SysLoginLogService {

    @Override
    public PageResult<SysLoginLogDTO> pageLoginLogList(LoginLogParam param) {
        QueryWrapper<SysLoginLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(StringUtils.isNotBlank(param.getLoginName()), SysLoginLog::getLoginName, param.getLoginName())
                .between(SysLoginLog::getLoginTime, param.getLoginStartTime(), param.getLoginEndTime())
                .orderByDesc(SysLoginLog::getId);
        Page<SysLoginLog> page = this.page(new Page<>(param.getPageNum(), param.getPageSize()), queryWrapper);

        List<SysLoginLogDTO> collect = page.getRecords().stream().map(e -> {
            SysLoginLogDTO dto = new SysLoginLogDTO();
            BeanUtils.copyProperties(e, dto);
            return dto;
        }).collect(Collectors.toList());
        return PageResult.ok(page.getTotal(), page.getCurrent(), collect);
    }
}
