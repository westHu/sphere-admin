package com.sphere.pay.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SysCurrentPwdReq {

    /**
     * 当前账户密码
     */
    @NotNull(message = "currentPwd is required")
    private String currentPwd;
}
