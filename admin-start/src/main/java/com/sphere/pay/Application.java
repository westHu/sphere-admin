package com.sphere.pay;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sphere.pay.db.entity.SysRole;
import com.sphere.pay.db.entity.SysUser;
import com.sphere.pay.param.SysRoleAddParam;
import com.sphere.pay.param.SysUserAddParam;
import com.sphere.pay.service.sys.SysResourceService;
import com.sphere.pay.service.sys.SysRoleService;
import com.sphere.pay.service.sys.SysUserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

import static com.sphere.pay.AdminConstant.LIMIT_SQL;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class Application implements CommandLineRunner {

    @Resource
    SysUserService sysUserService;
    @Resource
    SysRoleService sysRoleService;
    @Resource
    SysResourceService sysResourceService;

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Jakarta")); //FIXME
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("===> Congratulations sphere!");
        log.info("===> sphere admin start success, time:{}, args:{}", LocalDateTime.now(), args);

        //初始化SYS角色用户
        initSysRole();
        initSysUser();

    }

    private void initSysRole() {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysRole::getRoleName, "administrator").last(LIMIT_SQL);
        SysRole sysRole = sysRoleService.getOne(queryWrapper);
        if (Objects.isNull(sysRole)) {
            SysRoleAddParam addParam = new SysRoleAddParam();
            addParam.setRoleName("admin");
            addParam.setResourceIdList(List.of(1L));
            sysRoleService.addSysRole(addParam);
            log.info("超级角色已添加");
        }
    }

    private void initSysUser() {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getLoginName, "admin").last(LIMIT_SQL);
        SysUser sysUser = sysUserService.getOne(queryWrapper);
        if (Objects.isNull(sysUser)) {
            SysUserAddParam addParam = new SysUserAddParam();
            addParam.setLoginName("admin");
            addParam.setUserName("admin");
            addParam.setUserType(1);
            addParam.setEmail("admin@admin.com");
            addParam.setPhone("123");
            addParam.setArea(1);
            addParam.setRoleIdList(List.of(1L));
            sysUserService.addSysUser(addParam);
            log.info("管理员已添加");
        }
    }

}
