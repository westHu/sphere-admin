package com.sphere.pay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum TradeTypeEnum {

    /**
     * 按顺序
     * 收款、出款、充值、转账、提现, 退款
     */
    PAY(1, "收款"),
    CASH(2, "出款"),
    RECHARGE(3, "充值"),
    TRANSFER(4, "转账"),
    WITHDRAW(5, "提现"),
    REFUND(6, "退款"),
    UNKNOWN(99, "未知"),
    ;

    private final Integer code;
    private final String name;


    /**
     * codeToEnum
     */
    public static TradeTypeEnum codeToEnum(Integer code) {
        if (Objects.isNull(code)) {
            return UNKNOWN;
        }
        return Arrays.stream(TradeTypeEnum.values())
                .filter(e -> e.getCode().equals(code))
                .findAny()
                .orElse(UNKNOWN);
    }


    /**
     * 通过订单号来判断订单类型
     */
    public static TradeTypeEnum tradeNoToTradeType(String tradeNo) {
        return Optional.ofNullable(tradeNo).map(e -> e.substring(2, 3))
                .map(Integer::parseInt)
                .map(TradeTypeEnum::codeToEnum)
                .orElse(TradeTypeEnum.UNKNOWN);
    }
}
