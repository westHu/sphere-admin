package com.sphere.pay.remote.settle.param;

import com.sphere.pay.remote.PageExchangeParam;
import lombok.Data;

@Data
public class AccountSnapshotStatementGroupExchangeParam extends PageExchangeParam {

    /**
     * 开始日期
     */
    private String accountStartDate;

    /**
     * 结束日期
     */
    private String accountEndDate;

}
