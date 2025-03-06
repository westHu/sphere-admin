package com.sphere.pay.controller;


import com.sphere.pay.controller.request.AdminUploadReq;
import com.sphere.pay.enums.UploadTypeEnum;
import com.sphere.pay.result.Result;
import com.sphere.pay.service.CommonService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 公共API
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class CommonController {

    @Resource
    CommonService commonService;

    /**
     * 文件上传
     */
    @PostMapping("/upload")
    public Mono<Result<String>> upload(AdminUploadReq req) {
        UploadTypeEnum uploadType = UploadTypeEnum.codeOf(req.getUploadType());
        log.info("file update param={}", uploadType.name());
        return commonService.upload(uploadType, req.getMultipartFile(), req.getFileMd5());
    }

}
