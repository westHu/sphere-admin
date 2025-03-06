package com.sphere.pay.utils;


import com.sphere.pay.exception.AdminException;

import java.util.UUID;

public class StorageUtil {

    private StorageUtil() {
        throw new AdminException("Utility classes should not have public constructors");
    }

    /**
     * 上传商户数据到Storage
     */
    public static String uploadFileName(String dic, String fileName) {
        return dic + UUID.randomUUID() + getFileExtension(fileName);
    }


    /**
     * 文件后缀名
     */
    public static String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }

        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex != -1 && lastDotIndex < fileName.length() - 1) {
            return fileName.substring(lastDotIndex);
        } else {
            return "";
        }
    }
}
