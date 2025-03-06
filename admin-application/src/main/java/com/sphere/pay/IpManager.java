package com.sphere.pay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ServerWebExchange;

/**
 * ip simple_tools
 *
 * @author West.Hu
 **/
@Slf4j
public class IpManager {
    private static final String UNKNOWN = "UNKNOWN";

    private static final String IP_HEADER_NAME = "Nginx-Security-Ip";

    public static String getIp(ServerWebExchange exchange) {
        return "0.0.0.0";
    }

}