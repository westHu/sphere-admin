package com.sphere.pay.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 返回实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ErrorResult extends BaseResult {

    public ErrorResult() {
        super();
    }

}

