package com.sphere.pay.controller.sys;

import cn.hutool.json.JSONUtil;
import com.sphere.pay.controller.request.SysLockLogReq;
import com.sphere.pay.convert.SysLockLogConverter;
import com.sphere.pay.dto.SysLoginLogDTO;
import com.sphere.pay.param.SysLockLogParam;
import com.sphere.pay.result.PageResult;
import com.sphere.pay.service.sys.SysLockLogService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/admin")
public class SysLockLogController {

    @Resource
    SysLockLogConverter sysLockLogConverter;
    @Resource
    SysLockLogService sysLockLogService;

    /**
     * 分页锁定日志
     */
    @PostMapping("/pageLockLogList")
    public Mono<PageResult<SysLoginLogDTO>> pageLockLogList(@RequestBody @Validated SysLockLogReq req) {
        log.info("pageLockLogList req={}", JSONUtil.toJsonStr(req));
        SysLockLogParam param = sysLockLogConverter.convertSysLockLogParam(req);
        PageResult<SysLoginLogDTO> page = sysLockLogService.pageLockLogList(param);
        return Mono.just(page);
    }


}
