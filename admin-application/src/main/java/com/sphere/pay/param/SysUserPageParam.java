package com.sphere.pay.param;

import lombok.Data;

/**
 * @author west
 */
@Data
public class SysUserPageParam extends PageParam {

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
    private Integer area;

}
