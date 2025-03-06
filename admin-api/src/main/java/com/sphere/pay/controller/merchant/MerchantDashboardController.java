package com.sphere.pay.controller.merchant;


import com.sphere.pay.dto.dashboard.DashboardDTO;
import com.sphere.pay.service.sys.SysDashboardService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 测试API
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class MerchantDashboardController {

    @Resource
    SysDashboardService sysDashboardService;

    @PostMapping(value = "/merchantDashboard")
    public Mono<DashboardDTO> merchantDashboard() {
        log.info("merchantDashboard");
        return Mono.just(sysDashboardService.sysDashboard());
    }

}
