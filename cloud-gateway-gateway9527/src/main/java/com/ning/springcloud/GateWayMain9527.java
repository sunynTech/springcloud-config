package com.ning.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author sunyuning
 * @date 2024/3/7 15:11
 * @description: GateWayMain9527
 */
@SpringBootApplication
@EnableEurekaClient
public class GateWayMain9527 {
    /**
     * cloud-provider-payment8001看看controller的访问地址
     * get/lb
     * 我们目前不想暴露8001端口，希望在8001外面套一层9527
     */
    public static void main(String[] args) {
        SpringApplication.run(GateWayMain9527.class, args);
    }
}
