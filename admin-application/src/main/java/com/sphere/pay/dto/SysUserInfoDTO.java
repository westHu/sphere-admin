package com.sphere.pay.dto;

import lombok.Data;

import java.util.List;

@Data
public class SysUserInfoDTO {

    /**
     * 登录名称
     */
    private String realName;

    /**
     * 登录者角色
     */
    private List<String> roles;
}
