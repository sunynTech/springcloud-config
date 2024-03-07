package com.ning.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author sunyuning
 * @date 2024/3/4 20:37
 * @description: PaymentController
 */
@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/zk")
    public String paymentZk() {
        return "SpringCloud with zookeeper：" + serverPort + "\t" + UUID.randomUUID();
    }
}
