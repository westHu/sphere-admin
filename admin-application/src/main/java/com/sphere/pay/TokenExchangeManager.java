package com.sphere.pay;


import com.sphere.pay.exception.AdminException;
import com.sphere.pay.exception.ExceptionCode;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

import java.util.Optional;

/**
 * 获取登录商户
 */
public class TokenExchangeManager {

    /**
     * 搞到token
     */
    public static String getTokenInHeader(ServerWebExchange exchange) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String token = headers.getFirst(AdminConstant.LOGIN_SYS_TOKEN);
        return token != null ? token : "";
    }

    /**
     * 搞到token
     */
    public static String getTokenInCookie(ServerWebExchange exchange) {
        return Optional.of(exchange.getRequest())
                .map(ServerHttpRequest::getCookies)
                .map(e -> e.getFirst(AdminConstant.LOGIN_SYS_TOKEN))
                .map(HttpCookie::getValue)
                .orElseThrow(() -> new AdminException(ExceptionCode.UNAUTHORIZED));
    }

}
