package com.sphere.pay.service.impl;

import cn.hutool.core.lang.Assert;
import com.sphere.pay.config.UploadProperties;
import com.sphere.pay.enums.UploadTypeEnum;
import com.sphere.pay.exception.AdminException;
import com.sphere.pay.result.Result;
import com.sphere.pay.service.CommonService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class CommonServiceImpl implements CommonService {

    @Resource
    UploadProperties uploadProperties;


    @Override
    public Mono<Result<String>> upload(UploadTypeEnum uploadType, FilePart filePart, String fileMd5) {
        log.info("upload. uploadType={}, fileMd5={}", uploadType, fileMd5);
        return uploadLocal(uploadType, filePart);
    }


    //--------------------------------------------------------------------------------------------------------------


    /**
     * 上传本地
     */
    private Mono<Result<String>> uploadLocal(UploadTypeEnum uploadType, FilePart filePart) {
        String originalFilename = filePart.filename();
        if (StringUtils.isBlank(originalFilename)) {
            throw new AdminException("file name is not support!");
        }
        String fileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));

        File dic = null;
        if (uploadType.equals(UploadTypeEnum.NOTIFICATION)) {
            dic = new File(uploadProperties.getNotification());
        } else if (uploadType.equals(UploadTypeEnum.ANNOUNCEMENT)) {
            dic = new File(uploadProperties.getAnnouncement());
        } else if (uploadType.equals(UploadTypeEnum.EXAMINE)) {
            dic = new File(uploadProperties.getExamine());
        }

        if (Objects.isNull(dic)) {
            throw new AdminException("file base dic not create!");
        }

        if (!dic.exists()) {
            boolean mkdir = dic.mkdir();
            Assert.isTrue(mkdir, () -> new AdminException("mkdir upload file error"));
        }

        String finalName = dic.getPath() + File.separator + fileName;
        log.info("upload finalName={}", finalName);
        return filePart.transferTo(new File(finalName)).then(Mono.defer(() -> Mono.just(Result.ok(finalName))));
    }
}
