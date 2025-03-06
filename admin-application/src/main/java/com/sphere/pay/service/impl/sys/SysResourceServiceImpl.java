package com.sphere.pay.service.impl.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sphere.pay.AdminConstant;
import com.sphere.pay.assembler.ApplicationConverter;
import com.sphere.pay.db.entity.SysResource;
import com.sphere.pay.db.entity.SysRoleResource;
import com.sphere.pay.db.mapper.SysResourceMapper;
import com.sphere.pay.dto.SysResourceDTO;
import com.sphere.pay.exception.AdminException;
import com.sphere.pay.param.SysResourceAddParam;
import com.sphere.pay.param.SysResourceTreeParam;
import com.sphere.pay.param.SysResourceUpdateParam;
import com.sphere.pay.service.sys.SysResourceService;
import com.sphere.pay.service.sys.SysRoleResourceService;
import com.sphere.pay.utils.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author west
 */
@Slf4j
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements SysResourceService {

    @jakarta.annotation.Resource
    SysRoleResourceService sysRoleResourceService;
    @jakarta.annotation.Resource
    ApplicationConverter applicationConverter;


    @Override
    public List<SysResourceDTO> treeResourceList(SysResourceTreeParam param) {
        QueryWrapper<SysResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Objects.nonNull(param.getResourceId()), SysResource::getId, param.getResourceId())
                .eq(Objects.nonNull(param.getVisible()), SysResource::getVisible, param.getVisible())
                .eq(StringUtils.isNotBlank(param.getResourceName()), SysResource::getResourceName,
                        param.getResourceName());
        List<SysResource> sysResourceList = this.list(queryWrapper);

        List<SysResourceDTO> sysResourceDTOList = applicationConverter.convertResourceDTO(sysResourceList);

        //获取menu tree
        return TreeUtil.makeTree(sysResourceDTOList,
                x -> x.getParentId() == 0, (x, y) -> x.getId().equals(y.getParentId()), SysResourceDTO::setChildren);
    }

    @Override
    public List<SysResource> getResourceListByRole(Long id) {
        QueryWrapper<SysRoleResource> roleResourceQuery = new QueryWrapper<>();
        roleResourceQuery.lambda().eq(SysRoleResource::getRoleId, id);
        List<SysRoleResource> sysRoleResourceList = sysRoleResourceService.list(roleResourceQuery);
        if (CollectionUtils.isEmpty(sysRoleResourceList)) {
            return null;
        }

        List<Long> roleIdList = sysRoleResourceList.stream().map(SysRoleResource::getResourceId).collect(Collectors.toList());
        QueryWrapper<SysResource> roleQuery = new QueryWrapper<>();
        roleQuery.lambda().in(SysResource::getId, roleIdList);
        return this.list(roleQuery);
    }

    @Override
    public boolean addSysResource(SysResourceAddParam param) {
        QueryWrapper<SysResource> resourceQuery = new QueryWrapper<>();
        resourceQuery.lambda().eq(SysResource::getParentId, param.getParentId())
                .eq(SysResource::getResourceName, param.getResourceName())
                .last(AdminConstant.LIMIT_SQL);
        SysResource sysResource = this.getOne(resourceQuery);
        if (Objects.nonNull(sysResource)) {
            throw new AdminException("Menu has exist. " + param.getResourceName());
        }

        sysResource = new SysResource();
        sysResource.setResourceName(param.getResourceName());
        sysResource.setParentId(param.getParentId());
        sysResource.setOrderNum(param.getOrderNum());
        sysResource.setUrl(param.getUrl());
        sysResource.setVisible(true);
        sysResource.setIsRefresh(true);
        sysResource.setIcon(param.getIcon());
        return this.save(sysResource);
    }

    @Override
    public boolean updateSysResource(SysResourceUpdateParam param) {
        UpdateWrapper<SysResource> resourceUpdate = new UpdateWrapper<>();
        resourceUpdate.lambda()
                .set(Objects.nonNull(param.getParentId()), SysResource::getParentId, param.getParentId())
                .set(StringUtils.isNotBlank(param.getResourceName()), SysResource::getResourceName,
                        param.getResourceName())
                .set(Objects.nonNull(param.getOrderNum()), SysResource::getOrderNum, param.getOrderNum())
                .set(Objects.nonNull(param.getVisible()), SysResource::getVisible, param.getVisible())
                .eq(SysResource::getId, param.getId());
        return this.update(resourceUpdate);
    }
}
