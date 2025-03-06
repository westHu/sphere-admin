package com.sphere.pay;


import com.sphere.pay.dto.token.TokenRoleDTO;
import com.sphere.pay.dto.token.TokenUserDTO;
import com.sphere.pay.exception.AdminException;
import io.netty.util.concurrent.FastThreadLocal;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * 获取登录商户
 */
public class SysRequestManager {

    public static FastThreadLocal<TokenUserDTO> THREAD_LOCAL_BASE_USER = new FastThreadLocal<>();
    public static FastThreadLocal<List<TokenRoleDTO>> THREAD_LOCAL_BASE_ROLE = new FastThreadLocal<>();

    /**
     * 获取登录商户
     */
    public static TokenUserDTO getCurrentUser() {
        TokenUserDTO tokenUserDTO = THREAD_LOCAL_BASE_USER.get();
        if (Objects.isNull(tokenUserDTO)) {
            throw new AdminException("用户不存在");
        }
        return tokenUserDTO;
    }

    /**
     * 获取角色
     */
    public static List<TokenRoleDTO> getCurrentRole() {
        List<TokenRoleDTO> tokenRoleDTOList = THREAD_LOCAL_BASE_ROLE.get();
        if (CollectionUtils.isEmpty(tokenRoleDTOList)) {
            throw new AdminException("角色不存在");
        }
        return tokenRoleDTOList;
    }


    /**
     * 设置登录商户
     */
    public static void setCurrentUser(TokenUserDTO userDTO) {
        THREAD_LOCAL_BASE_USER.set(userDTO);
    }

    /**
     * 设置角色
     */
    public static void setCurrentRole(List<TokenRoleDTO> roleDTOList) {
        THREAD_LOCAL_BASE_ROLE.set(roleDTOList);
    }

}
