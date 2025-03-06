package com.sphere.pay.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.http.codec.multipart.FilePart;

@Data
public class AdminUploadReq {

    /**
     * 文件
     */
    @NotNull(message = "multipartFile is required")
    private FilePart multipartFile;

    /**
     * 上传类型
     */
    private String uploadType;

    /**
     * 文件MD5
     */
    @NotBlank(message = "fileMd5 is required")
    private String fileMd5;

}
