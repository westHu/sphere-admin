package com.sphere.pay.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TableHeaderUpdateReq {

    /**
     * 用户ID
     */
    @NotNull(message = "userId is required")
    private Long userId;

    /**
     * 表格名称
     */
    @NotBlank(message = "tableName is required")
    private String tableName;

    /**
     * 表头格式
     */
    @NotBlank(message = "tableHeader is required")
    private String tableHeader;
}
