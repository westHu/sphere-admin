package com.sphere.pay.controller.request;

import com.sphere.pay.config.valid.EnumValid;
import com.sphere.pay.enums.AreaEnum;
import lombok.Data;

/**
 * @author west
 */
@Data
public class SysUserPageReq extends PageReq {

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 账号状态
     */
    private Integer status;

    /**
     * 地区
     */
    @EnumValid(target = AreaEnum.class, transferMethod = "getCode", message = "area code not support")
    private Integer area;

}
