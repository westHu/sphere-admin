package com.sphere.pay.param;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author west
 */
@Data
public class MerchantMenuUpdateParam {

    /**
     * 菜单ID
     */
    @NotNull(message = "resourceId is required")
    private Long id;

    /**
     * 菜单名称
     */
    private String enName;

    /**
     * 顺序
     */
    private Integer orderNum;

    /**
     * 是否可见
     */
    private Boolean visible;

}
