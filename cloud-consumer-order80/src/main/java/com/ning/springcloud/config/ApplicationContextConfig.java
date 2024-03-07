package com.ning.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author sunyuning
 * @date 2024/3/3 19:38
 * @description: ApplicationContextConfig
 */
@Configuration
public class ApplicationContextConfig {
    /**
     * Spring 在初始化时会扫描 @Configuration 注解的配置类，
     * 发现其中有 @Bean 注解的方法时会将这些方法执行，
     * 将返回的实例纳入到 Spring 容器中进行管理。
     * 当其他组件需要使用这些实例时，Spring 会直接从容器中获取已经创建好的实例，
     * 而不会再次调用 @Bean 方法来获取实例。
     *
     * 因此，虽然没有显式调用 getRestTemplate() 方法，
     * 但是通过 @Resource 注入 RestTemplate 实例时，
     * Spring 会直接从容器中获取已经创建好的，带有负载均衡能力的 RestTemplate 实例，
     * 可以直接使用负载均衡功能。
     */
    @Bean
//    @LoadBalanced// 使用@LoadBalanced注解赋予RestTemplate负载均衡的能力
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
