package com.sphere.pay.result;

import cn.hutool.json.JSONUtil;
import com.sphere.pay.exception.AdminException;
import com.sphere.pay.exception.ExceptionCode;
import com.sphere.pay.utils.ValidationUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * 基础返回实体
 */
@Data
@Slf4j
public class BaseResult {

    /**
     * 业务码
     */
    private Integer code;

    /**
     * 业务信息
     */

    private String message;
    /**
     * 链路id
     */

    private String traceId = UUID.randomUUID().toString();

    public BaseResult() {
    }

    public BaseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    /**
     * 统一判断
     */
    public static <T> T parse(Result<T> result) {
        return parse(result, true);
    }

    public static <T> T parse(Result<T> result, boolean validate) {
        log.info("Post result={}", JSONUtil.toJsonStr(result));

        if (Objects.isNull(result)) {
            log.error("Post error: result is null");
            throw new AdminException("post error: result is null");
        }

        if (!ExceptionCode.SUCCESS.getCode().equals(result.getCode())) {
            log.error("Post error:{}", result.getMessage());
            throw new AdminException(result.getMessage());
        }

        if (Objects.isNull(result.getData())) {
            return null;
        }

        T t = result.getData();
        if (validate && !ValidationUtil.validateTrue(t)) {
            throw new AdminException("Validate no pass");
        }

        return t;
    }

    public static <T> List<T> parse(PageResult<T> pageResult) {
        log.info("Post pageResult={}", JSONUtil.toJsonStr(pageResult));

        if (Objects.isNull(pageResult)) {
            log.error("Post error: pageResult is null");
            throw new AdminException("post error: pageResult is null");
        }

        if (!ExceptionCode.SUCCESS.getCode().equals(pageResult.getCode())) {
            log.error("Post error:{}", pageResult.getMessage());
            throw new AdminException(pageResult.getMessage());
        }

        List<T> data = pageResult.getData();
        if (CollectionUtils.isEmpty(data)) {
            return null;
        }
        return data;
    }

}


