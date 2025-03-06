package com.sphere.pay.controller.sys;

import cn.hutool.json.JSONUtil;
import com.sphere.pay.controller.request.SysResourceAddReq;
import com.sphere.pay.controller.request.SysResourceTreeReq;
import com.sphere.pay.controller.request.SysResourceUpdateReq;
import com.sphere.pay.convert.ResourceConverter;
import com.sphere.pay.dto.SysResourceDTO;
import com.sphere.pay.param.SysResourceAddParam;
import com.sphere.pay.param.SysResourceTreeParam;
import com.sphere.pay.param.SysResourceUpdateParam;
import com.sphere.pay.result.Result;
import com.sphere.pay.service.sys.SysResourceService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;


/**
 * 资源API
 *
 * @author west
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class SysResourceController {

    @Resource
    SysResourceService sysResourceService;
    @Resource
    ResourceConverter resourceConverter;

    /**
     * 资源列表
     */
    @PostMapping("/treeSysResourceList")
    public Mono<Result<List<SysResourceDTO>>> treeResourceList(@RequestBody @Validated SysResourceTreeReq req) {
        log.info("treeResourceList req={}", JSONUtil.toJsonStr(req));
        SysResourceTreeParam param = resourceConverter.convertSysResourceTreeParam(req);
        List<SysResourceDTO> dtoList = sysResourceService.treeResourceList(param);
        return Mono.just(Result.ok(dtoList));
    }

    /**
     * 新增资源
     */
    @PostMapping("/addSysResource")
    public Mono<Result<Boolean>> addSysResource(@RequestBody @Validated SysResourceAddReq req) {
        log.info("addSysResource req={}", JSONUtil.toJsonStr(req));
        SysResourceAddParam param = resourceConverter.convertSysResourceAddParam(req);
        return Mono.just(Result.ok(sysResourceService.addSysResource(param)));
    }


    /**
     * 更新资源
     */
    @PostMapping("/updateSysResource")
    public Mono<Result<Boolean>> updateSysResource(@RequestBody @Validated SysResourceUpdateReq req) {
        log.info("updateSysResource req={}", JSONUtil.toJsonStr(req));
        SysResourceUpdateParam param = resourceConverter.convertSysResourceUpdateParam(req);
        return Mono.just(Result.ok(sysResourceService.updateSysResource(param)));
    }

}
