package com.sphere.pay.remote;

import com.sphere.pay.AdminConstant;
import com.sphere.pay.remote.merchant.MerchantApiService;
import com.sphere.pay.remote.payment.PaymentApiService;
import com.sphere.pay.remote.settle.SettleApiService;
import com.sphere.pay.remote.trade.TradeApiService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * 在此处添加备注信息
 */
@Configuration(proxyBeanMethods = false)
public class WebClientConfiguration {

    @Resource
    WebClient.Builder webClientBuilder;

    /**
     * merchant WebClient配置
     */
    @Bean
    public TradeApiService tradeServiceApi() {
        WebClient webClient = webClientBuilder.baseUrl(AdminConstant.URL_PAYMENT).build();
        return HttpServiceProxyFactory.builder().clientAdapter(WebClientAdapter.forClient(webClient)).build().createClient(TradeApiService.class);
    }

    /**
     * merchant WebClient配置
     */
    @Bean
    public MerchantApiService merchantServiceApi() {
        WebClient webClient = webClientBuilder.baseUrl(AdminConstant.URL_PAYMENT).build();
        return HttpServiceProxyFactory.builder().clientAdapter(WebClientAdapter.forClient(webClient)).build().createClient(MerchantApiService.class);
    }

    /**
     * payment WebClient配置
     */
    @Bean
    public PaymentApiService paymentApiService() {
        WebClient webClient = webClientBuilder.baseUrl(AdminConstant.URL_PAYMENT).build();
        return HttpServiceProxyFactory.builder().clientAdapter(WebClientAdapter.forClient(webClient)).build().createClient(PaymentApiService.class);
    }

    /**
     * settle WebClient配置
     */
    @Bean
    public SettleApiService settleApiService() {
        WebClient webClient = webClientBuilder.baseUrl(AdminConstant.URL_PAYMENT).build();
        return HttpServiceProxyFactory.builder().clientAdapter(WebClientAdapter.forClient(webClient)).build().createClient(SettleApiService.class);
    }

}