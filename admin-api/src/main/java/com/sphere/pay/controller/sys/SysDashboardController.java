package com.sphere.pay.controller.sys;


import com.sphere.pay.dto.dashboard.DashboardDTO;
import com.sphere.pay.service.sys.SysDashboardService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 首页API
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class SysDashboardController {

    @Resource
    SysDashboardService sysDashboardService;

    @PostMapping(value = "/sysDashboard")
    public Mono<DashboardDTO> sysDashboard() {
        log.info("sysDashboard");
        return Mono.just(sysDashboardService.sysDashboard());
    }


}
