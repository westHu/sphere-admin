package com.sphere.pay.remote;

import lombok.Data;

@Data
public class PageExchangeParam {

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 页条数
     */
    private Integer pageSize = 10;
}
