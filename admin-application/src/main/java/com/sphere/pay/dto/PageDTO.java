package com.sphere.pay.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageDTO<T> {

    private Long total;

    private List<T> data;

    public static <T> PageDTO<T> of(Long total, List<T> data) {
        PageDTO<T> pageDTO = new PageDTO<>();
        pageDTO.setTotal(total);
        pageDTO.setData(data);
        return pageDTO;
    }
}
