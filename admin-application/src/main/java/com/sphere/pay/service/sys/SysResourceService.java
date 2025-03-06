package com.sphere.pay.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sphere.pay.db.entity.SysResource;
import com.sphere.pay.dto.SysResourceDTO;
import com.sphere.pay.param.SysResourceAddParam;
import com.sphere.pay.param.SysResourceTreeParam;
import com.sphere.pay.param.SysResourceUpdateParam;

import java.util.List;

/**
 * @author west
 */
public interface SysResourceService extends IService<SysResource> {

    List<SysResourceDTO> treeResourceList(SysResourceTreeParam sysResourceTreeParam);

    List<SysResource> getResourceListByRole(Long id);

    boolean addSysResource(SysResourceAddParam param);

    boolean updateSysResource(SysResourceUpdateParam param);
}
