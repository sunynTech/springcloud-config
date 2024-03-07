package com.ning.springcloud.filter;

import cn.hutool.core.date.DateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author sunyuning
 * @date 2024/3/7 19:47
 * @description: MyLogGateWayFilter
 */
@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {
    /**
     * 这段代码是一个示例的全局过滤器，用于在 Spring Cloud Gateway 中进行请求过滤和处理，使用了Spring WebFlux 。
     * 首先，通过实现 filter 方法，该方法接收 ServerWebExchange 对象和 GatewayFilterChain 对象作为参数，用于处理网关请求和调用下一个过滤器链。
     * 接着通过 exchange.getRequest().getQueryParams().getFirst("uname") 获取请求参数中名为 "uname" 的参数值。
     * 如果获取到的 uname 为 null，则说明请求中没有提供用户名，设置响应状态码为 HttpStatus.NOT_ACCEPTABLE，表示不可接受的请求。然后通过 exchange.getResponse().setComplete() 完成响应并返回。
     * 如果获取到了 uname，则说明请求合法，调用 chain.filter(exchange) 继续执行后续的过滤器链。
     * 总体来说，这段代码是一个简单的全局过滤器示例，用于在网关中对请求进行过滤，检查请求中是否包含特定参数，并根据条件进行处理。如果请求中未包含指定参数，则拒绝请求；否则允许请求通过并继续执行后续的过滤器链。
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("*******进入全局过滤器： MyLogGateWayFilter："+new DateTime());
        //获取请求参数中名为 "uname" 的参数值。
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if (uname == null){
            log.info("*******用户名为null，非法用户，(╯°口°)╯︵ ┻━┻");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        /**
         * 加载过滤器顺序，数字越小优先级越高
         */
        return 0;
    }
}
