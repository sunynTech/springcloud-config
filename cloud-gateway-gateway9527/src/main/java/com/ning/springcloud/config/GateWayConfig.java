package com.ning.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sunyuning
 * @date 2024/3/7 16:29
 * @description: GateWayConfig
 */
@Configuration
public class GateWayConfig {
    /**
     * 配置了一个id为route-name的路由规则
     * 当访问地址http://localhost:9527/guonei时会自动转发到地址：http://news.baidu.com/guonei
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("path_route_ning",
                        r -> r.path("/guonei")
                                .uri("http://news.baidu.com/guonei")).build();
    }
}
