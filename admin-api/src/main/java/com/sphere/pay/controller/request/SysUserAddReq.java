package com.sphere.pay.controller.request;

import com.sphere.pay.AdminConstant;
import com.sphere.pay.enums.AreaEnum;
import com.sphere.pay.enums.UserTypeEnum;
import com.sphere.pay.exception.AdminException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
public class SysUserAddReq extends SysCurrentPwdReq {

    /**
     * 登录账号
     */
    @NotNull(message = "loginName is required")
    @Length(max = 30, message = "loginName length max 30")
    private String loginName;

    /**
     * 用户昵称
     */
    @NotNull(message = "userName is required")
    @Length(max = 30, message = "loginName length max 30")
    private String userName;

    /**
     * 用户类型
     */
    private Integer userType = UserTypeEnum.REGISTER_USER.getCode();

    /**
     * 邮箱
     */
    @NotNull(message = "email is required")
    @Length(max = 50, message = "email length max 50")
    @Email(message = "email box format illegal")
    private String email;

    /**
     * 电话号码
     */
    @NotNull(message = "phone is required")
    @Length(max = 11, message = "phone length max 11")
    private String phone;

    /**
     * 地区
     */
    private Integer area = AreaEnum.INDONESIA.getCode();

    /**
     * 角色列表
     */
    @NotEmpty(message = "roleIdList is empty")
    private List<Long> roleIdList;

    /**
     * 不能新增“admin”的账户
     */
    public String getLoginName() {
        if (loginName.equalsIgnoreCase(AdminConstant.USER_INIT)) {
            throw new AdminException("'admin' is native merchantLogin name");
        }
        return loginName;
    }

    public String getUserName() {
        if (userName.equalsIgnoreCase(AdminConstant.USER_INIT)) {
            throw new AdminException("'admin' is native username");
        }
        return userName;
    }
}
