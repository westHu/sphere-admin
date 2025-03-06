package com.sphere.pay.controller.sys;

import cn.hutool.json.JSONUtil;
import com.sphere.pay.controller.request.LoginLogReq;
import com.sphere.pay.convert.SysLoginConverter;
import com.sphere.pay.dto.SysLoginLogDTO;
import com.sphere.pay.param.LoginLogParam;
import com.sphere.pay.result.PageResult;
import com.sphere.pay.service.sys.SysLoginLogService;
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
public class SysLoginLogController {

    @Resource
    SysLoginLogService sysLoginLogService;
    @Resource
    SysLoginConverter sysLoginConverter;

    /**
     * 分页查询登录日志
     */
    @PostMapping("/pageLoginLogList")
    public Mono<PageResult<SysLoginLogDTO>> pageLoginLogList(@RequestBody @Validated LoginLogReq req) {
        log.info("pageLoginLogList req={}", JSONUtil.toJsonStr(req));
        LoginLogParam param = sysLoginConverter.convertLoginLogParam(req);
        PageResult<SysLoginLogDTO> page = sysLoginLogService.pageLoginLogList(param);
        return Mono.just(page);
    }

}
