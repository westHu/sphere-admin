package com.sphere.pay.controller.response;

import com.sphere.pay.db.entity.BaseEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoginLogVO extends BaseEntity {

    /**
     * ID
     */
    private Long id;

    /**
     * merchant merchantLogin name
     */
    private String loginName;

    /**
     * merchantLogin/sysLogout/frozen
     */
    private Integer loginType;

    /**
     * ip
     */
    private String loginIp;

    /**
     * result true/false
     */
    private boolean loginStatus;

    /**
     * when
     */
    private LocalDateTime loginTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
