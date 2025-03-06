package com.sphere.pay.db.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_lock_log")
public class SysLockLog extends BaseEntity {

    /**
     * 锁人
     */
    private String lockName;

    /**
     * 状态
     */
    private boolean lockStatus;

    /**
     * 时间
     */
    private LocalDateTime lockTime;

    /**
     * 解锁人
     */
    private String unlockName;

    /**
     * 解锁时间
     */
    private LocalDateTime unlockTime;

}
