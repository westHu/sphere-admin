package com.sphere.pay.service.merchant;


import com.sphere.pay.dto.MerchantLoginDTO;
import com.sphere.pay.param.MerchantLoginParam;
import org.springframework.web.server.ServerWebExchange;

public interface MerchantLoginService {

    MerchantLoginDTO merchantLogin(MerchantLoginParam param);

    boolean logout(ServerWebExchange exchange);
}
