package com.sphere.pay.controller.sys;

import cn.hutool.json.JSONUtil;
import com.sphere.pay.controller.request.SysOptLogReq;
import com.sphere.pay.convert.SysOptLogConverter;
import com.sphere.pay.dto.SysLoginLogDTO;
import com.sphere.pay.param.SysOptLogParam;
import com.sphere.pay.result.PageResult;
import com.sphere.pay.service.sys.SysOptLogService;
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
public class SysOptLogController {

    @Resource
    SysOptLogConverter sysOptLogConverter;
    @Resource
    SysOptLogService sysOptLogService;

    /**
     * 分页查询登录日志
     */
    @PostMapping("/pageOptLogList")
    public Mono<PageResult<SysLoginLogDTO>> pageOptLogList(@RequestBody @Validated SysOptLogReq req) {
        log.info("pageOptLogList req={}", JSONUtil.toJsonStr(req));
        SysOptLogParam param = sysOptLogConverter.convertSysOptLogParam(req);
        PageResult<SysLoginLogDTO> page = sysOptLogService.pageOptLogList(param);
        return Mono.just(page);
    }


}
