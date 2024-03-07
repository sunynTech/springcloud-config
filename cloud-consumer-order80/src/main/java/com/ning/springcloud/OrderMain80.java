package com.ning.springcloud;

import com.ning.myRule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author sunyuning
 * @date 2024/3/3 19:31
 * @description: OrderMain80
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = MySelfRule.class)
public class OrderMain80 {
    /**
     * 官方文档明确给出了警告:
     * 这个自定义配置类不能放在@ComponentScan所扫描的当前包下以及子包下，
     * 否则我们自定义的这个配置类就会被所有的Ribbon客户端所共享，达不到特殊化定制的目的了。
     * （也就是说不要将Ribbon配置类与主启动类同包）
     * 放到启动类之外的包，也就是myRule包里
     */
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
