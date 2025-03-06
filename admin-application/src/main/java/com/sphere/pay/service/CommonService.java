package com.sphere.pay.service;

import com.sphere.pay.enums.UploadTypeEnum;
import com.sphere.pay.result.Result;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

public interface CommonService {

    Mono<Result<String>> upload(UploadTypeEnum uploadType, FilePart filePart, String fileMd5);

}
