package com.sphere.pay.service.impl.sys;

import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sphere.pay.AdminConstant;
import com.sphere.pay.SysRequestManager;
import com.sphere.pay.assembler.ApplicationConverter;
import com.sphere.pay.db.entity.SysResource;
import com.sphere.pay.db.entity.SysRole;
import com.sphere.pay.db.entity.SysRoleResource;
import com.sphere.pay.db.entity.SysUser;
import com.sphere.pay.db.entity.SysUserRole;
import com.sphere.pay.dto.SysLoginDTO;
import com.sphere.pay.dto.SysResourceDTO;
import com.sphere.pay.dto.SysUserInfoDTO;
import com.sphere.pay.dto.token.TokenRoleDTO;
import com.sphere.pay.dto.token.TokenUserDTO;
import com.sphere.pay.enums.UserStatusEnum;
import com.sphere.pay.exception.AdminException;
import com.sphere.pay.param.LoginResetPwdParam;
import com.sphere.pay.param.SysLoginParam;
import com.sphere.pay.service.sys.SysLoginLogService;
import com.sphere.pay.service.sys.SysLoginService;
import com.sphere.pay.service.sys.SysResourceService;
import com.sphere.pay.service.sys.SysRoleResourceService;
import com.sphere.pay.service.sys.SysRoleService;
import com.sphere.pay.service.sys.SysUserRoleService;
import com.sphere.pay.service.sys.SysUserService;
import com.sphere.pay.utils.JWTUtil;
import com.sphere.pay.utils.Md5Util;
import com.sphere.pay.utils.TreeUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author dh
 */
@Slf4j
@Service
public class SysLoginServiceImpl implements SysLoginService {


    @Resource
    SysUserService sysUserService;
    @Resource
    SysRoleService sysRoleService;
    @Resource
    SysResourceService sysResourceService;
    @Resource
    SysUserRoleService sysUserRoleService;
    @Resource
    SysRoleResourceService sysRoleResourceService;
    @Resource
    BCryptPasswordEncoder passwordEncoder;
    @Resource
    SysLoginLogService sysLoginLogService;
    @Resource
    ApplicationConverter applicationConverter;

    @Value("${spring.profiles.active}")
    String profile;


    @Override
    public SysLoginDTO sysLogin(SysLoginParam param, ServerWebExchange exchange) {
        log.info("sysLogin param={}", JSONUtil.toJsonStr(param));

        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getLoginName, param.getUsername()).last(AdminConstant.LIMIT_SQL);
        SysUser sysUser = sysUserService.getOne(queryWrapper);
        Assert.notNull(sysUser, () -> new AdminException("用户不存在"));

        UserStatusEnum userStatusEnum = UserStatusEnum.codeToEnum(sysUser.getStatus());
        Assert.equals(userStatusEnum, UserStatusEnum.NORMAL, () -> new AdminException("用户状态错误"));

        boolean pwdMatch = passwordEncoder.matches(Md5Util.getMD5Code(param.getPassword()), sysUser.getPassword());
        Assert.isTrue(pwdMatch, () -> new AdminException("抱歉，密码错误"));

        //生成token
        int maxAge =  12 * 60 * 60;
        TokenUserDTO tokenUserDTO = new TokenUserDTO();
        tokenUserDTO.setId(sysUser.getId());
        tokenUserDTO.setLoginName(sysUser.getLoginName());
        tokenUserDTO.setUserName(sysUser.getUserName());
        tokenUserDTO.setUserType(sysUser.getUserType());

        Map<String, String> claimMap = new HashMap<>();
        claimMap.put(AdminConstant.LOGIN_CLAIM_SYS_USER, JSONUtil.toJsonStr(tokenUserDTO));
        String token = JWTUtil.getToken(claimMap, maxAge);
        ResponseCookie cookie = ResponseCookie.from(AdminConstant.LOGIN_SYS_TOKEN, token)
                .path("/")
                .httpOnly(true)
                .maxAge(maxAge)
                .build();
        exchange.getResponse().addCookie(cookie);
        //exchange.getResponse().getHeaders().add(AdminConstant.PARAM_LOGIN_USERNAME, sysUser.getLoginName()); FIXME

        SysLoginDTO sysLoginDTO = new SysLoginDTO();
        sysLoginDTO.setAccessToken(token);
        return sysLoginDTO;
    }

    @Override
    public SysUserInfoDTO getSysUserInfo(TokenUserDTO currentUser, ServerWebExchange exchange) {
        log.info("getSysUserInfo param={}", JSONUtil.toJsonStr(currentUser));

        Long id = currentUser.getId();
        SysUser sysUser = sysUserService.getById(id);
        Assert.notNull(sysUser, () -> new AdminException("用户不存在"));

        UserStatusEnum userStatusEnum = UserStatusEnum.codeToEnum(sysUser.getStatus());
        Assert.equals(userStatusEnum, UserStatusEnum.NORMAL, () -> new AdminException("用户状态错误"));

        SysUserInfoDTO userInfoDTO = new SysUserInfoDTO();
        userInfoDTO.setRealName(sysUser.getUserName());
        userInfoDTO.setRoles(new ArrayList<>());
        return userInfoDTO;
    }


    @Override
    public List<SysResourceDTO> getSysMenu(ServerWebExchange exchange) {
        //如果是管理员
        List<TokenRoleDTO> roleList = SysRequestManager.getCurrentRole();
        boolean isAdmin = roleList.stream().anyMatch(e ->
                StringUtils.containsIgnoreCase(e.getRoleName(), AdminConstant.ROLE_INIT));
        if (isAdmin) {
            QueryWrapper<SysResource> resourceQuery = new QueryWrapper<>();
            resourceQuery.lambda()
                    .eq(SysResource::getVisible, Boolean.TRUE)
                    .orderByAsc(SysResource::getOrderNum);
            List<SysResource> sysResourceList = sysResourceService.list(resourceQuery);
            List<SysResourceDTO> sysResourceDTOList = applicationConverter.convertResourceDTO(sysResourceList);
            return TreeUtil.makeTree(sysResourceDTOList,
                    x -> x.getParentId() == 0, (x, y) -> x.getId().equals(y.getParentId()), SysResourceDTO::setChildren);
        }

        //非管理员
        TokenUserDTO user = SysRequestManager.getCurrentUser();
        QueryWrapper<SysUserRole> userQuery = new QueryWrapper<>();
        userQuery.lambda().eq(SysUserRole::getUserId, user.getId());
        List<SysUserRole> sysUserRoleList = sysUserRoleService.list(userQuery);
        if (CollectionUtils.isEmpty(sysUserRoleList)) {
            throw new AdminException("user not assigned role");
        }
        List<Long> roleIdList = sysUserRoleList.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        QueryWrapper<SysRoleResource> roleQuery = new QueryWrapper<>();
        roleQuery.lambda().in(SysRoleResource::getRoleId, roleIdList);
        List<SysRoleResource> sysRoleResourceList = sysRoleResourceService.list(roleQuery);
        List<Long> resourceIdList =
                sysRoleResourceList.stream().map(SysRoleResource::getResourceId).collect(Collectors.toList());

        QueryWrapper<SysResource> resourceQuery = new QueryWrapper<>();
        resourceQuery.lambda()
                .in(CollectionUtils.isNotEmpty(resourceIdList),
                        SysResource::getId, resourceIdList)
                .eq(SysResource::getVisible, Boolean.TRUE)
                .orderByAsc(SysResource::getOrderNum);
        List<SysResource> sysResourceList = sysResourceService.list(resourceQuery);
        List<SysResourceDTO> sysResourceDTOList = applicationConverter.convertResourceDTO(sysResourceList);
        return TreeUtil.makeTree(sysResourceDTOList,
                x -> x.getParentId() == 0, (x, y) -> x.getId().equals(y.getParentId()), SysResourceDTO::setChildren);
    }

    @Override
    public void sysLogout(ServerWebExchange exchange) {
    }


    @Override
    public void changeSysPwd(LoginResetPwdParam param) {
        log.info("changeSysPwd param={}", JSONUtil.toJsonStr(param));

        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getLoginName, param.getUsername()).last(AdminConstant.LIMIT_SQL);
        SysUser sysUser = sysUserService.getOne(queryWrapper);
        if (Objects.isNull(sysUser)) {
            throw new AdminException("user not exist");
        }

        //校验旧密码正确性
        boolean pwdMatch = passwordEncoder.matches(Md5Util.getMD5Code(param.getOldPassword()), sysUser.getPassword());
        if (!pwdMatch) {
            log.error("oldPassword don't match. param={}", JSONUtil.toJsonStr(param));
            throw new AdminException("oldPassword don't match");
        }

        //设置新密码
        UpdateWrapper<SysUser> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .set(SysUser::getPassword, passwordEncoder.encode(Md5Util.getMD5Code(param.getNewPassword())))
                .set(SysUser::getPwdUpdateDate, LocalDateTime.now())
                .eq(SysUser::getId, sysUser.getId());
        sysUserService.update(updateWrapper);
    }


    @Override
    public void resetSysPwd(Long id) {
        log.info("resetSysPwd param={}", id);

        SysUser sysUser = sysUserService.getById(id);
        if (Objects.isNull(sysUser)) {
            throw new AdminException("user not exist");
        }

        UpdateWrapper<SysUser> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .set(SysUser::getPassword, passwordEncoder.encode(Md5Util.getMD5Code(AdminConstant.PASSWORD_DEFAULT)))
                .set(SysUser::getPwdUpdateDate, LocalDateTime.now())
                .eq(SysUser::getId, id);
        sysUserService.update(updateWrapper);
    }


}
