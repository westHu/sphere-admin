package com.sphere.pay.dto.dashboard;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DashboardChannelDTO {

    List<ChannelProportionDTO> channelProportionList = new ArrayList<>();
}
