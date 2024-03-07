package com.ning.springcloud.controller;

import com.ning.springcloud.entities.CommonResult;
import com.ning.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sunyuning
 * @date 2024/3/6 09:30
 * @description: OrderFeignController
 */
@RestController
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<?> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        // 客户端openFeign默认等待1秒钟，但是服务端的超时方法故意睡眠了3秒，超时会报错
        return paymentFeignService.paymentFeignTimeout();
    }
}
