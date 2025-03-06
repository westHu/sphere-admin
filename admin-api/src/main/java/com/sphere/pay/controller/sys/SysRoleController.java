package com.sphere.pay.controller.sys;

import cn.hutool.json.JSONUtil;
import com.sphere.pay.config.opt.OptLogFlag;
import com.sphere.pay.controller.request.IdReq;
import com.sphere.pay.controller.request.SysRoleAddReq;
import com.sphere.pay.controller.request.SysRolePageReq;
import com.sphere.pay.controller.request.SysRoleUpdateReq;
import com.sphere.pay.convert.ResourceConverter;
import com.sphere.pay.convert.SysRoleConverter;
import com.sphere.pay.dto.SysRoleDTO;
import com.sphere.pay.param.SysRoleAddParam;
import com.sphere.pay.param.SysRolePageParam;
import com.sphere.pay.param.SysRoleUpdateParam;
import com.sphere.pay.result.PageResult;
import com.sphere.pay.result.Result;
import com.sphere.pay.service.sys.SysResourceService;
import com.sphere.pay.service.sys.SysRoleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


/**
 * 角色API
 *
 * @author west
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class SysRoleController {


    @Resource
    SysRoleService sysRoleService;
    @Resource
    SysResourceService sysResourceService;
    @Resource
    SysRoleConverter sysRoleConverter;
    @Resource
    ResourceConverter resourceConverter;

    /**
     * 角色列表
     */
    @PostMapping("/pageSysRoleList")
    public Mono<PageResult<SysRoleDTO>> pageSysRoleList(@RequestBody @Validated SysRolePageReq req) {
        log.info("pageSysRoleList req={}", JSONUtil.toJsonStr(req));
        SysRolePageParam param = sysRoleConverter.convertSysRolePageParam(req);
        PageResult<SysRoleDTO> page = sysRoleService.pageRoleList(param);
        return Mono.just(page);
    }
    
    /**
     * 角色详情
     */
    @PostMapping("/getSysRoleDetail")
    public Mono<Result<SysRoleDTO>> getSysRoleDetail(@RequestBody @Validated IdReq req) {
        log.info("getSysRoleDetail req={}", JSONUtil.toJsonStr(req));
        SysRoleDTO sysRoleDTO = sysRoleService.getSysRoleDetail(req.getId());
        return Mono.just(Result.ok(sysRoleDTO));
    }

    /**
     * 新增角色
     */
    @OptLogFlag
    @PostMapping("/addSysRole")
    public Mono<Result<Void>> addSysRole(@RequestBody @Validated SysRoleAddReq req) {
        log.info("addSysRole req={}", JSONUtil.toJsonStr(req));
        SysRoleAddParam param = sysRoleConverter.convertSysRoleAddParam(req);
        sysRoleService.addSysRole(param);
        return Mono.just(Result.ok());
    }


    /**
     * 修改角色
     */
    @OptLogFlag
    @PostMapping("/updateSysRole")
    public Mono<Result<Void>> updateSysRole(@RequestBody @Validated SysRoleUpdateReq req) {
        log.info("updateSysRole req={}", JSONUtil.toJsonStr(req));
        SysRoleUpdateParam param = sysRoleConverter.convertSysRoleUpdateParam(req);
        sysRoleService.updateSysRole(param);
        return Mono.just(Result.ok());
    }

    /**
     * 删除角色
     */
    @OptLogFlag
    @PostMapping("/deleteSysRole")
    public Mono<Result<Void>> deleteSysRole(@RequestBody @Validated IdReq req) {
        log.info("deleteSysRole req={}", JSONUtil.toJsonStr(req));
        sysRoleService.deleteSysRole(req.getId());
        return Mono.just(Result.ok());
    }
}
