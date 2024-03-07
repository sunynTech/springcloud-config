package com.ning.springcloud.service;

import com.ning.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author sunyuning
 * @date 2024/3/6 09:24
 * @description: PaymentFeignService
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    /**
     * 照服务提供端的controller完全抄
     * 与方法名无关，只与路径有关
     */
    @GetMapping(value = "/payment/get/{id}")
    CommonResult<?> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/feign/timeout")
    String paymentFeignTimeout();
}
