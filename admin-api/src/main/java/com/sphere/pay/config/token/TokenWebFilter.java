package com.sphere.pay.config.token;

import cn.hutool.json.JSONUtil;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Payload;
import com.sphere.pay.AdminConstant;
import com.sphere.pay.MerchantRequestManager;
import com.sphere.pay.SysRequestManager;
import com.sphere.pay.TokenExchangeManager;
import com.sphere.pay.dto.token.TokenMerchantDTO;
import com.sphere.pay.dto.token.TokenMerchantOperatorDTO;
import com.sphere.pay.dto.token.TokenRoleDTO;
import com.sphere.pay.dto.token.TokenUserDTO;
import com.sphere.pay.exception.AdminException;
import com.sphere.pay.exception.ExceptionCode;
import com.sphere.pay.utils.JWTUtil;
import io.micrometer.common.lang.NonNullApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


@Slf4j
@NonNullApi
@Component
public class TokenWebFilter implements WebFilter {

    private final static List<String> nonTokenPaths = Arrays.asList(
            "/actuator/health",
            "/admin/hello",
            "/admin/auth/login"
            );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getPath().value();
        log.info("tokenWebFilter path={}", path);

        /*if (true) {
            return chain.filter(exchange);
        }*/

        //免登录URL
        boolean anyMatch = nonTokenPaths.stream().anyMatch(path::contains);
        log.info("tokenWebFilter anyMatch={}", anyMatch);
        if (anyMatch) {
            return chain.filter(exchange);
        }

        //admin
        if (path.contains("/admin")) {
            return doAdmin(exchange, chain);
        }

        //merchant-admin
        if (path.contains("/merchant")) {
            return doMerchantAdmin(exchange, chain);
        }

        return Mono.error(new AdminException(ExceptionCode.UNAUTHORIZED));
    }


    /**
     * admin
     */
    private Mono<Void> doAdmin(ServerWebExchange exchange, WebFilterChain chain) {

        //执行认证
        String token = TokenExchangeManager.getTokenInCookie(exchange);
        if (StringUtils.isBlank(token)) {
            log.info("token interceptor token is miss");
            return Mono.error(new AdminException(ExceptionCode.UNAUTHORIZED, "please merchant login first"));
        }

        // 验证 token
        Map<String, Object> claimMap = verifyToken(token);
        TokenUserDTO tokenUserDTO = Optional.of(claimMap)
                .map(e -> e.get(AdminConstant.LOGIN_CLAIM_SYS_USER))
                .map(Objects::toString)
                .map(e -> JSONUtil.toBean(e, TokenUserDTO.class))
                .orElseThrow(() -> new AdminException(ExceptionCode.UNAUTHORIZED.getCode(), "Token User Error"));

        List<TokenRoleDTO> tokenRoleDTOList = Optional.of(claimMap)
                .map(e -> e.get(AdminConstant.LOGIN_CLAIM_SYS_ROLE))
                .map(Objects::toString)
                .map(e -> JSONUtil.toList(e, TokenRoleDTO.class))
                .orElseThrow(() -> new AdminException(ExceptionCode.UNAUTHORIZED.getCode(), "Token User Error"));

        SysRequestManager.setCurrentUser(tokenUserDTO);
        SysRequestManager.setCurrentRole(tokenRoleDTOList);
        return chain.filter(exchange);
    }


    /**
     * merchant-admin
     */
    private Mono<Void> doMerchantAdmin(ServerWebExchange exchange, WebFilterChain chain) {

        //执行认证
        String token = TokenExchangeManager.getTokenInCookie(exchange);
        if (StringUtils.isBlank(token)) {
            log.info("token interceptor token is miss");
            return Mono.error(new AdminException(ExceptionCode.UNAUTHORIZED, "please merchant login first"));
        }

        // 验证 token
        Map<String, Object> claimMap = verifyToken(token);
        TokenMerchantDTO tokenUserDTO = Optional.of(claimMap)
                .map(e -> e.get(AdminConstant.LOGIN_CLAIM_SYS_USER))
                .map(Objects::toString)
                .map(e -> JSONUtil.toBean(e, TokenMerchantDTO.class))
                .orElseThrow(() -> new AdminException(ExceptionCode.UNAUTHORIZED.getCode(), "Token User Error"));

        TokenMerchantOperatorDTO tokenMerchantOperatorDTO = Optional.of(claimMap)
                .map(e -> e.get(AdminConstant.LOGIN_CLAIM_SYS_ROLE))
                .map(Objects::toString)
                .map(e -> JSONUtil.toBean(e, TokenMerchantOperatorDTO.class))
                .orElseThrow(() -> new AdminException(ExceptionCode.UNAUTHORIZED.getCode(), "Token User Error"));

        MerchantRequestManager.setCurrentMerchant(tokenUserDTO);
        MerchantRequestManager.setCurrentMerchantOperator(tokenMerchantOperatorDTO);
        return chain.filter(exchange);
    }


    /**
     * verifyToken token
     */
    private Map<String, Object> verifyToken(String token) {
        try {
            DecodedJWT verify = JWTUtil.verifyToken(token);
            return Optional.ofNullable(verify)
                    .map(Payload::getClaims)
                    .map(e -> e.get(AdminConstant.LOGIN_SYS_CLAIM))
                    .map(Claim::asMap)
                    .orElseThrow(() -> new AdminException(ExceptionCode.UNAUTHORIZED.getCode(), "Token Verify Error"));
        } catch (SignatureVerificationException e) {
            log.error("Token interceptor signatureVerificationException:", e);
            throw new AdminException(ExceptionCode.UNAUTHORIZED.getCode(), "Signature Verification Error");

        } catch (TokenExpiredException e) {
            log.error("Token interceptor tokenExpiredException:", e);
            throw new AdminException(ExceptionCode.UNAUTHORIZED.getCode(), "Token Expired Error");

        } catch (AlgorithmMismatchException e) {
            log.error("Token interceptor algorithmMismatchException:", e);
            throw new AdminException(ExceptionCode.UNAUTHORIZED.getCode(), "Algorithm Mismatch Error");

        } catch (Exception e) {
            log.error("Token interceptor exception:", e);
            throw new AdminException(ExceptionCode.UNAUTHORIZED.getCode(), "Token Error");
        }
    }


}
