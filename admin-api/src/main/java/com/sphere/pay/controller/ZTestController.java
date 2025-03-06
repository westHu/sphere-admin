package com.sphere.pay.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 测试API
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class ZTestController {

    @GetMapping(value = "/hello")
    public Mono<String> hello() {
        log.info("==============================================");
        return Mono.just("Hello Admin");
    }


}
