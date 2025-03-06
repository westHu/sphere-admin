package com.sphere.pay.controller.request;

import lombok.Data;

@Data
public class PlugInEncryptReq {

    /**
     * 前缀
     */
    private String prefix;

    /**
     * 后缀
     */
    private String suffix;

    /**
     * 加密文本
     */
    private String encryptText;

    /**
     * 加密盐
     */
    private String salt;

}
