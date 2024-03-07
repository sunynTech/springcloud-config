package com.ning.springcloud.controller;

import com.ning.springcloud.entities.CommonResult;
import com.ning.springcloud.entities.Payment;
import com.ning.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author sunyuning
 * @date 2024/3/3 16:16
 * @description: PaymentController
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult<?> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*****插入结果：" + result);
        if (result > 0) {
            return new CommonResult<>(200, "插入数据库成功,serverPort：" + serverPort, result);
        } else {
            return new CommonResult<>(444, "插入数据库失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<?> page(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果：" + payment);
        if (Objects.nonNull(payment)) {
            return new CommonResult<>(200, "查询成功,serverPort：" + serverPort, payment);
        } else {
            return new CommonResult<>(444, "没有对应记录，查询ID：" + id, null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        //获取全部服务
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("*****service：" + service);
        }
        //某一个服务下面的所有实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            //ServiceId或叫service名称，主机号，端口号，uri(路径)
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        //返回服务接口
        return serverPort;
    }
}
