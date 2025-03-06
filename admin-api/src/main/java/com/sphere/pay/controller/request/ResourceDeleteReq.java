package com.sphere.pay.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author west
 */
@Data
public class ResourceDeleteReq {

    @NotNull(message = "id is required")
    private Long id;

}
