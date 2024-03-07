package com.ning.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author sunyuning
 * @date 2024/3/5 20:27
 * @description: LoadBalancer
 */
public interface LoadBalance {
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
