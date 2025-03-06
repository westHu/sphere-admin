package com.sphere.pay.param;

import lombok.Data;

@Data
public class TableHeaderUpdateParam {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户ID
     */
    private String tableName;


    /**
     * 表头格式
     */
    private String tableHeader;
}
