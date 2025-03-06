package com.sphere.pay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "admin.upload.path")
public class UploadProperties {

    /**
     * 通知地址
     */
    private String notification;

    /**
     * 公告地址
     */
    private String announcement;

    /**
     * 审核相关地址
     */
    private String examine = "/data/upload/sphere/examine";

}
