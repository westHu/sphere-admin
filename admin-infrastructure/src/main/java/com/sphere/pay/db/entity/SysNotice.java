package com.sphere.pay.db.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author dh
 */
@Data
@EqualsAndHashCode(callSuper = true)
//@TableName(value = "sys_notice")
public class SysNotice extends BaseEntity {

    /**
     * 通知ID
     */
    private Integer noticeId;
    /**
     * 标题
     */
    private Integer noticeTitle;

    /**
     * 公告类型（1通知 2公告）
     */
    private Integer noticeType;

    /**
     * 内容
     */
    private Integer noticeContent;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private Integer remark;
}
