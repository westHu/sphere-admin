package com.sphere.pay.service.impl.sys;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sphere.pay.SysRequestManager;
import com.sphere.pay.config.GoogleConfig;
import com.sphere.pay.db.entity.SysLockLog;
import com.sphere.pay.db.entity.SysUser;
import com.sphere.pay.db.entity.SysUserRole;
import com.sphere.pay.db.mapper.SysUserMapper;
import com.sphere.pay.dto.AuthShowDTO;
import com.sphere.pay.dto.SysUserDTO;
import com.sphere.pay.dto.token.TokenUserDTO;
import com.sphere.pay.enums.ProfileEnum;
import com.sphere.pay.enums.UserStatusEnum;
import com.sphere.pay.exception.AdminException;
import com.sphere.pay.param.AuthCodeVerifyParam;
import com.sphere.pay.param.SysUserAddParam;
import com.sphere.pay.param.SysUserPageParam;
import com.sphere.pay.param.SysUserUpdateParam;
import com.sphere.pay.result.PageResult;
import com.sphere.pay.service.sys.SysLockLogService;
import com.sphere.pay.service.sys.SysUserRoleService;
import com.sphere.pay.service.sys.SysUserService;
import com.sphere.pay.utils.Md5Util;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.sphere.pay.AdminConstant.PASSWORD_DEFAULT;

/**
 * @author west
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    BCryptPasswordEncoder passwordEncoder;
    @Resource
    SysUserRoleService sysUserRoleService;
    @Resource
    SysLockLogService sysLockLogService;

    @Value("${spring.profiles.active:DEV}")
    String profile;


    @Override
    public PageResult<SysUserDTO> pageUserList(SysUserPageParam param) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Objects.nonNull(param.getArea()), SysUser::getArea, param.getArea())
                .likeRight(StringUtils.isNotBlank(param.getUserName()), SysUser::getUserName, param.getUserName())
                .eq(Objects.nonNull(param.getStatus()), SysUser::getStatus, param.getStatus());
        Page<SysUser> page = this.page(new Page<>(param.getPageNum(), param.getPageSize()), queryWrapper);

        List<SysUserDTO> collect = page.getRecords().stream().map(e -> {
            SysUserDTO dto = new SysUserDTO();
            BeanUtils.copyProperties(e, dto);
            return dto;
        }).collect(Collectors.toList());
        return PageResult.ok(page.getTotal(), page.getCurrent(), collect);
    }

    @Override
    public SysUserDTO getSysUserDetail(Long id) {
        SysUser sysUser = this.getById(id);
        if (Objects.nonNull(sysUser)) {
            SysUserDTO dto = new SysUserDTO();
            BeanUtils.copyProperties(sysUser, dto);
            return dto;
        }
        return null;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSysUser(SysUserAddParam param) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getLoginName, param.getLoginName());
        SysUser sysUser = this.getOne(queryWrapper);
        Assert.isNull(sysUser, () -> new AdminException("用户已存在"));

        sysUser = new SysUser();
        BeanUtils.copyProperties(param, sysUser);
        sysUser.setSex(0);
        sysUser.setPassword(passwordEncoder.encode(Md5Util.getMD5Code(PASSWORD_DEFAULT)));
        sysUser.setStatus(UserStatusEnum.NORMAL.getCode());
        sysUser.setCreateTime(LocalDateTime.now());
        this.save(sysUser);

        Long userId = sysUser.getId();
        List<Long> roleIdList = param.getRoleIdList();
        if (CollectionUtils.isNotEmpty(roleIdList)) {
            List<SysUserRole> sysUserRoleList = new HashSet<>(roleIdList).stream().map(roleId -> {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(userId);
                sysUserRole.setRoleId(roleId);
                return sysUserRole;
            }).collect(Collectors.toList());
            sysUserRoleService.saveBatch(sysUserRoleList);
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Long id) {
        SysUser sysUser = this.getById(id);
        Assert.notNull(sysUser, () -> new AdminException("delete user error"));

        QueryWrapper<SysUserRole> userRoleDelete = new QueryWrapper<>();
        userRoleDelete.lambda().eq(SysUserRole::getUserId, sysUser.getId());
        sysUserRoleService.remove(userRoleDelete);

        this.removeById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSysUser(SysUserUpdateParam param) {
        SysUser sysUser = this.getById(param.getUserId());
        Assert.notNull(sysUser, () -> new AdminException("用户不存在"));

        UpdateWrapper<SysUser> userUpdate = new UpdateWrapper<>();
        userUpdate.lambda()
                .set(StringUtils.isNotBlank(param.getUserName()), SysUser::getUserName, param.getUserName())
                .set(StringUtils.isNotBlank(param.getEmail()), SysUser::getEmail, param.getEmail())
                .set(StringUtils.isNotBlank(param.getPhone()), SysUser::getPhone, param.getPhone())
                .set(Objects.nonNull(param.getStatus()), SysUser::getStatus, param.getStatus())
                .eq(SysUser::getId, param.getUserId());
        this.update(userUpdate);


        //先删除再增加
        if (CollectionUtils.isNotEmpty(param.getRoleIdList())) {
            QueryWrapper<SysUserRole> userRoleDelete = new QueryWrapper<>();
            userRoleDelete.lambda().eq(SysUserRole::getUserId, sysUser.getId());
            sysUserRoleService.remove(userRoleDelete);

            List<SysUserRole> sysUserRoleList = param.getRoleIdList().stream().map(roleId -> {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(sysUser.getId());
                sysUserRole.setRoleId(roleId);
                sysUserRole.setCreateTime(LocalDateTime.now());
                return sysUserRole;
            }).collect(Collectors.toList());
            sysUserRoleService.saveBatch(sysUserRoleList);
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean lockSysUser(Long id) {
        SysUser sysUser = this.getById(id);
        Assert.notNull(sysUser, () -> new AdminException("lockSysUser user error"));

        //变更用户状态
        UpdateWrapper<SysUser> userUpdate = new UpdateWrapper<>();
        userUpdate.lambda().set(SysUser::getStatus, UserStatusEnum.LOCKED.getCode())
                .eq(SysUser::getId, id);
        this.update(userUpdate);

        //新增锁住用户记录
        SysLockLog sysLockLog = new SysLockLog();
        sysLockLog.setLockName(sysUser.getLoginName());
        sysLockLog.setLockStatus(true);
        sysLockLog.setLockTime(LocalDateTime.now());
        sysLockLog.setCreateTime(LocalDateTime.now());
        sysLockLogService.save(sysLockLog);

        return true;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean unlockSysUser(Long id, ServerWebExchange exchange) {
        TokenUserDTO currentUser = SysRequestManager.getCurrentUser();

        //变更用户状态
        UpdateWrapper<SysUser> userUpdate = new UpdateWrapper<>();
        userUpdate.lambda().set(SysUser::getStatus, UserStatusEnum.NORMAL.getCode())
                .eq(SysUser::getId, id);
        this.update(userUpdate);

        SysUser sysUser = this.getById(id);
        if (Objects.nonNull(sysUser)) {
            UpdateWrapper<SysLockLog> lockLogUpdate = new UpdateWrapper<>();
            lockLogUpdate.lambda().set(SysLockLog::isLockStatus, false)
                    .set(SysLockLog::getUnlockName, currentUser.getUserName())
                    .set(SysLockLog::getUnlockTime, LocalDateTime.now())
                    .eq(SysLockLog::getId, id);
            sysLockLogService.update(lockLogUpdate);
        }
        return true;
    }


    @Override
    public boolean verifyAuthCode(AuthCodeVerifyParam param, ServerWebExchange exchange) {
        TokenUserDTO currentUser = SysRequestManager.getCurrentUser();

        if (!profile.equalsIgnoreCase(ProfileEnum.PROD.name())) {
            return true;
        }

        SysUser sysUser = this.getById(currentUser.getId());
        if (Objects.isNull(sysUser)) {
            throw new AdminException("user not exist");
        }

        if (StringUtils.isAllBlank(param.getSecret(), sysUser.getSecret())) {
            throw new AdminException("user not bind merchantLogin auth");
        }
        if (StringUtils.isNotBlank(sysUser.getSecret())) {
            Boolean verifyCode = GoogleConfig.verifyCode(param.getAuthCode(), sysUser.getSecret());
            if (!verifyCode) {
                throw new AdminException("The verification code is incorrect");
            }
        } else {
            Boolean verify = GoogleConfig.verifyCode(param.getAuthCode(), param.getSecret());
            if (!verify) {
                throw new AdminException("The verification code is not bound or the verification code is incorrect");
            }
            UpdateWrapper<SysUser> userUpdate = new UpdateWrapper<>();
            userUpdate.lambda().set(SysUser::getSecret, param.getSecret())
                    .eq(SysUser::getId, currentUser.getId());
            this.update(userUpdate);
        }
        return true;
    }

    @Override
    public AuthShowDTO showAuthenticator() {
        String secret = GoogleConfig.generateSecretKey();
        AuthShowDTO showDTO = new AuthShowDTO();
        showDTO.setSecret(secret);
        showDTO.setAuthUrl(GoogleConfig.getQrUrl("Payment", profile.toUpperCase(), secret));
        return showDTO;
    }


    @Override
    public boolean unbindAuthenticator(Long userId, String authCode, ServerWebExchange exchange) {
        UpdateWrapper<SysUser> userUpdate = new UpdateWrapper<>();
        userUpdate.lambda().set(SysUser::getSecret, null)
                .eq(SysUser::getId, userId);
        this.update(userUpdate);

        return true;
    }

    @Override
    public String showCurrentAuth(ServerWebExchange exchange) {
        TokenUserDTO currentUser = SysRequestManager.getCurrentUser();
        SysUser sysUser = this.getById(currentUser.getId());
        Assert.notNull(sysUser, () -> new AdminException("unlockSysUser user error"));
        return GoogleConfig.getQrUrl("Payment", profile.toUpperCase(), sysUser.getSecret());
    }


}
