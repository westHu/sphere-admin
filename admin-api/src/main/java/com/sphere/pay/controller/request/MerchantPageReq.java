package com.sphere.pay.controller.request;

import com.sphere.pay.enums.AreaEnum;
import com.sphere.pay.enums.UserStatusEnum;
import lombok.Data;

/**
 * @author west
 */
@Data
public class MerchantPageReq extends PageReq {

    /**
     * 地区
     */
    private AreaEnum area;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 帐号状态
     */
    private UserStatusEnum status;

}
