package com.sphere.pay.remote.payment.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PaymentChannelBalDTO {

    private String channelCode;

    private String channelName;

    private String balance;

    private Date recordTime;
}
