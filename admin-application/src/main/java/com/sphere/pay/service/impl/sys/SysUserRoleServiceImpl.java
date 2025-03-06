package com.sphere.pay.service.impl.sys;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sphere.pay.db.entity.SysUserRole;
import com.sphere.pay.db.mapper.SysUserRoleMapper;
import com.sphere.pay.service.sys.SysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author west
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole>
        implements SysUserRoleService {
}
