package com.sphere.pay.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MerchantLockLogDTO {

    /**
     * 主键id
     */
    private Long id;

    /**
     * lock merchant merchantLogin name
     */
    private String lockName;

    /**
     * merchantLogin/sysLogout/frozen
     */
    private boolean lockStatus;

    /**
     * when
     */
    private LocalDateTime lockTime;

    /**
     * unlock merchant merchantLogin name
     */
    private String unlockName;

    /**
     * when unlock
     */
    private LocalDateTime unlockTime;
}
