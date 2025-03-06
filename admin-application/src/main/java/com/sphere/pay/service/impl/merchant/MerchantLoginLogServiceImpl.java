package com.sphere.pay.service.impl.merchant;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sphere.pay.db.entity.MerchantLoginLog;
import com.sphere.pay.db.mapper.MerchantLoginLogMapper;
import com.sphere.pay.dto.MerchantLoginLogDTO;
import com.sphere.pay.param.MerchantLoginLogPageParam;
import com.sphere.pay.result.PageResult;
import com.sphere.pay.service.merchant.MerchantLoginLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MerchantLoginLogServiceImpl extends ServiceImpl<MerchantLoginLogMapper, MerchantLoginLog>
        implements MerchantLoginLogService {

    @Override
    public PageResult<MerchantLoginLogDTO> pageMerchantLoginLogList(MerchantLoginLogPageParam param) {
        String loginName = param.getLoginName();
        String startLoginTime = param.getStartLoginTime();
        String endLoginTime = param.getEndLoginTime();
        Integer pageNum = param.getPageNum();
        Integer pageSize = param.getPageSize();

        QueryWrapper<MerchantLoginLog> lockLogQuery = new QueryWrapper<>();
        lockLogQuery.lambda().eq(StringUtils.isNotBlank(loginName), MerchantLoginLog::getLoginName, loginName)
                .ge(StringUtils.isNotBlank(startLoginTime), MerchantLoginLog::getLoginTime, startLoginTime)
                .le(StringUtils.isNotBlank(endLoginTime), MerchantLoginLog::getLoginTime, endLoginTime);
        Page<MerchantLoginLog> page = this.page(new Page<>(pageNum, pageSize), lockLogQuery);

        List<MerchantLoginLogDTO> collect = page.getRecords().stream().map(e -> {
            MerchantLoginLogDTO dto = new MerchantLoginLogDTO();
            BeanUtil.copyProperties(e, dto);
            return dto;
        }).collect(Collectors.toList());

        return PageResult.ok(page.getTotal(), page.getCurrent(), collect);
    }
}
