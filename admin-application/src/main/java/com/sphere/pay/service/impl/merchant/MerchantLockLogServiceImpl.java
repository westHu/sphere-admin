package com.sphere.pay.service.impl.merchant;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sphere.pay.db.entity.MerchantLockLog;
import com.sphere.pay.db.mapper.MerchantLockLogMapper;
import com.sphere.pay.dto.MerchantLockLogDTO;
import com.sphere.pay.param.MerchantLockLogPageParam;
import com.sphere.pay.param.MerchantLockParam;
import com.sphere.pay.param.MerchantUnlockParam;
import com.sphere.pay.result.PageResult;
import com.sphere.pay.service.merchant.MerchantLockLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MerchantLockLogServiceImpl extends ServiceImpl<MerchantLockLogMapper, MerchantLockLog>
        implements MerchantLockLogService {

    @Override
    public PageResult<MerchantLockLogDTO> pageMerchantLockLogList(MerchantLockLogPageParam param) {
        String lockName = param.getLockName();
        String startLockTime = param.getStartLockTime();
        String endLockTime = param.getEndLockTime();
        Integer pageNum = param.getPageNum();
        Integer pageSize = param.getPageSize();

        QueryWrapper<MerchantLockLog> lockLogQuery = new QueryWrapper<>();
        lockLogQuery.lambda().eq(StringUtils.isNotBlank(lockName), MerchantLockLog::getLockName, lockName)
                .ge(StringUtils.isNotBlank(startLockTime), MerchantLockLog::getLockTime, startLockTime)
                .le(StringUtils.isNotBlank(endLockTime), MerchantLockLog::getLockTime, endLockTime);
        Page<MerchantLockLog> page = this.page(new Page<>(pageNum, pageSize), lockLogQuery);

        List<MerchantLockLogDTO> collect = page.getRecords().stream().map(e -> {
            MerchantLockLogDTO dto = new MerchantLockLogDTO();
            BeanUtil.copyProperties(e, dto);
            return dto;
        }).collect(Collectors.toList());
        return PageResult.ok(page.getTotal(), page.getCurrent(), collect);
    }

    @Override
    public MerchantLockLogDTO getMerchantLockLog(MerchantLockParam param) {
        return null;
    }

    @Override
    public boolean unLock4MerchantLogin(MerchantUnlockParam param) {
        MerchantLockLog lockLog = this.getById(param.getId());
        if (Objects.isNull(lockLog)) {
            return false;
        }

        //更新记录
        UpdateWrapper<MerchantLockLog> lockLogUpdate = new UpdateWrapper<>();
        lockLogUpdate.lambda().set(MerchantLockLog::getUnlockName, param.getUnlockName())
                .set(MerchantLockLog::getUnlockTime, LocalDateTime.now())
                .eq(MerchantLockLog::getId, lockLog.getId());
        return this.update(lockLogUpdate);
    }


}
