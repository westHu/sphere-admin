package com.sphere.pay.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sphere.pay.db.entity.SysRole;
import com.sphere.pay.dto.SysRoleDTO;
import com.sphere.pay.param.SysRoleAddParam;
import com.sphere.pay.param.SysRolePageParam;
import com.sphere.pay.param.SysRoleUpdateParam;
import com.sphere.pay.result.PageResult;

/**
 * @author west
 */
public interface SysRoleService extends IService<SysRole> {

    PageResult<SysRoleDTO> pageRoleList(SysRolePageParam sysRolePageParam);

    SysRoleDTO getSysRoleDetail(Long id);

    void addSysRole(SysRoleAddParam addParam);

    void updateSysRole(SysRoleUpdateParam updateParam);

    void deleteSysRole(Long id);

}
