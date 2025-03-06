package com.sphere.pay.controller.response;

import com.sphere.pay.dto.SysRoleDTO;
import com.sphere.pay.dto.SysUserDTO;
import lombok.Data;

import java.util.List;

/**
 * @author dh
 */
@Data
public class LoginVO {

    /**
     * 是否第一次登录
     */
    private Boolean updatePassword = false;

    /**
     * token
     */
    private String token;

    /**
     * 用户
     */
    private SysUserDTO user;

    /**
     * 角色
     */
    private List<SysRoleDTO> roleList;

    /**
     * 资源
     */
    private List<ResourceVO> resourceTree;

    /**
     * 登录时间
     */
    private String loginTime;

}
