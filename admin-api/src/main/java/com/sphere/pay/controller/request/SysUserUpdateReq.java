package com.sphere.pay.controller.request;

import com.sphere.pay.config.valid.EnumValid;
import com.sphere.pay.enums.SexEnum;
import com.sphere.pay.enums.UserStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * @author west
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserUpdateReq extends SysCurrentPwdReq {

    /**
     * 用户ID
     */
    @NotNull(message = "userId is required")
    private Integer userId;

    /**
     * 用户昵称
     */
    @Length(max = 30, message = "userName length max 30")
    private String userName;

    /**
     * 用户邮箱
     */
    @Length(max = 50, message = "email length max 50")
    private String email;

    /**
     * 用户电话
     */
    @Length(max = 11, message = "phone length max 11")
    private String phone;

    /**
     * 账号状态
     */
    @EnumValid(target = UserStatusEnum.class, transferMethod = "getCode", message = "status code not support")
    private Integer status;

    /**
     * 地区
     */
    @EnumValid(target = SexEnum.class, transferMethod = "getCode", message = "area code not support")
    private Integer area;

    /**
     * 角色列表
     */
    private List<Long> roleIdList;
}
