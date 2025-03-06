package com.sphere.pay.controller.request;

import lombok.Data;

/**
 * @author west
 */
@Data
public class PageReq {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

}
