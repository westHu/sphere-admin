package com.sphere.pay.db.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "merchant_opt_log")
public class MerchantOptLog extends BaseEntity {

    /**
     * 用户名
     */
    private String optName;

    /**
     * 方法
     */
    private String method;

    /**
     * 参数
     */
    private String params;

    /**
     * ip
     */
    private String ip;

    /**
     * 结果
     */
    private String result;

    /**
     * 操作时间
     */
    private LocalDateTime optTime;

}

