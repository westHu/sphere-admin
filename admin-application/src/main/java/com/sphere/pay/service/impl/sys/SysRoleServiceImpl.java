package com.sphere.pay.service.impl.sys;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sphere.pay.db.entity.SysRole;
import com.sphere.pay.db.entity.SysRoleResource;
import com.sphere.pay.db.mapper.SysRoleMapper;
import com.sphere.pay.dto.SysRoleDTO;
import com.sphere.pay.exception.AdminException;
import com.sphere.pay.param.SysRoleAddParam;
import com.sphere.pay.param.SysRolePageParam;
import com.sphere.pay.param.SysRoleUpdateParam;
import com.sphere.pay.result.PageResult;
import com.sphere.pay.service.sys.SysRoleResourceService;
import com.sphere.pay.service.sys.SysRoleService;
import com.sphere.pay.service.sys.SysUserRoleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author west
 */
@Slf4j
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Resource
    SysUserRoleService sysUserRoleService;
    @Resource
    SysRoleResourceService sysRoleResourceService;

    
    @Override
    public PageResult<SysRoleDTO> pageRoleList(SysRolePageParam param) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Objects.nonNull(param.getRoleId()), SysRole::getId, param.getRoleId())
                .likeRight(StringUtils.isNotBlank(param.getRoleName()), SysRole::getRoleName, param.getRoleName())
                .eq(Objects.nonNull(param.getStatus()), SysRole::getStatus, param.getStatus());

        Page<SysRole> page = this.page(new Page<>(param.getPageNum(), param.getPageSize()), queryWrapper);
        List<SysRoleDTO> collect = page.getRecords().stream().map(e -> {
            SysRoleDTO dto = new SysRoleDTO();
            BeanUtils.copyProperties(e, dto);
            return dto;
        }).collect(Collectors.toList());
        return PageResult.ok(page.getTotal(), page.getCurrent(), collect);
    }

    @Override
    public SysRoleDTO getSysRoleDetail(Long id) {
        SysRole sysRole = this.getById(id);
        if (Objects.nonNull(sysRole)) {
            SysRoleDTO dto = new SysRoleDTO();
            BeanUtils.copyProperties(sysRole, dto);
            return dto;
        }
        return null;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSysRole(SysRoleAddParam param) {
        QueryWrapper<SysRole> roleQuery = new QueryWrapper<>();
        roleQuery.lambda().eq(SysRole::getRoleName, param.getRoleName());
        SysRole sysRole = this.getOne(roleQuery);
        Assert.isNull(sysRole, () -> new AdminException("角色存在"));

        sysRole = new SysRole();
        sysRole.setRoleName(param.getRoleName());
        sysRole.setRoleKey("key");
        sysRole.setRoleSort(0);
        sysRole.setStatus(true);
        sysRole.setCreateTime(LocalDateTime.now());
        this.save(sysRole);

        Long roleId = sysRole.getId();

        List<SysRoleResource> sysRoleResourceList = new HashSet<>(param.getResourceIdList()).stream().map(resourceId -> {
            SysRoleResource sysRoleResource = new SysRoleResource();
            sysRoleResource.setRoleId(roleId);
            sysRoleResource.setResourceId(resourceId);
            sysRoleResource.setCreateTime(LocalDateTime.now());
            return sysRoleResource;
        }).collect(Collectors.toList());
        sysRoleResourceService.saveBatch(sysRoleResourceList);
    }

    
    @Override
    public void updateSysRole(SysRoleUpdateParam param) {
        SysRole sysRole = this.getById(param.getRoleId());
        Assert.notNull(sysRole, () -> new AdminException("角色不存在"));

        UpdateWrapper<SysRole> roleUpdate = new UpdateWrapper<>();
        roleUpdate.lambda()
                .set(StringUtils.isNotBlank(param.getRoleName()), SysRole::getRoleName, param.getRoleName())
                .set(Objects.nonNull(param.getStatus()), SysRole::getStatus, param.getStatus())
                .eq(SysRole::getId, param.getRoleId());
        this.update(roleUpdate);

        //先删除再增加
        if (CollectionUtils.isNotEmpty(param.getResourceIdList())) {
            QueryWrapper<SysRoleResource> roleResourceQuery = new QueryWrapper<>();
            roleResourceQuery.lambda().eq(SysRoleResource::getRoleId, sysRole.getId());
            sysRoleResourceService.remove(roleResourceQuery);

            List<SysRoleResource> sysRoleResourceList = param.getResourceIdList().stream().map(resourceId -> {
                SysRoleResource sysRoleResource = new SysRoleResource();
                sysRoleResource.setRoleId(sysRole.getId());
                sysRoleResource.setResourceId(resourceId);
                sysRoleResource.setCreateTime(LocalDateTime.now());
                return sysRoleResource;
            }).collect(Collectors.toList());
            sysRoleResourceService.saveBatch(sysRoleResourceList);
        }
    }

    
    @Override
    public void deleteSysRole(Long id) {
        SysRole sysRole = this.getById(id);
        Assert.notNull(sysRole, () -> new AdminException("delete role error"));

        QueryWrapper<SysRoleResource> roleResourceDelete = new QueryWrapper<>();
        roleResourceDelete.lambda().eq(SysRoleResource::getRoleId, sysRole.getId());
        sysRoleResourceService.remove(roleResourceDelete);

        this.removeById(id);
    }


}
