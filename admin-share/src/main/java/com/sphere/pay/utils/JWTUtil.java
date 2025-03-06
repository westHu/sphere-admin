package com.sphere.pay.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sphere.pay.AdminConstant;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class JWTUtil {

    //private static final String sign = SequenceNoUtil.randomNanoId(16);
    private static final String sign = "81a9fGo16K3Y8CbU";

    /**
     * 构建JWT token
     */
    public static String getToken(Map<String, String> claimMap, int maxAge) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, maxAge > 0 ? maxAge : AdminConstant.TOKEN_EXPIRED);

        JWTCreator.Builder builder = JWT.create();
        builder.withJWTId(UUID.randomUUID().toString())
                .withSubject(AdminConstant.LOGIN_SYS_SUBJECT)
                .withClaim(AdminConstant.LOGIN_SYS_CLAIM, claimMap);
        return builder.withExpiresAt(calendar.getTime()).sign(Algorithm.HMAC256(sign));
    }

    /**
     * 校验token
     */
    public static DecodedJWT verifyToken(String token) {
        return JWT.require(Algorithm.HMAC256(sign)).build().verify(token);
    }


}
