package com.sphere.pay.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SysLoginDTO {

    /**
     * 是否第一次登录
     */
    private Boolean updatePassword = false;

    /**
     * token
     */
    private String accessToken;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime = LocalDateTime.now();
}
