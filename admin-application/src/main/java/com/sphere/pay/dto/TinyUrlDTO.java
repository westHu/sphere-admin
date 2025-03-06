package com.sphere.pay.dto;

import lombok.Data;

import java.util.List;

@Data
public class TinyUrlDTO {

    private String code;

    private TinyUrlDataDTO data;

    private List<String> errors;

}
