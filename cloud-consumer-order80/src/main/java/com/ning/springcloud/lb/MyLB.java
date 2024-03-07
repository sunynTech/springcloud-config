package com.ning.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sunyuning
 * @date 2024/3/5 20:30
 * @description: MyLB
 */
@Component
public class MyLB implements LoadBalance {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 首先获取当前的值 current。
     * 接着根据当前值计算出下一个值 next，如果当前值已经达到最大值 2147483647，则将 next 设置为 0。
     * 然后使用 compareAndSet(current, next) 方法尝试将 AtomicInteger 的值从 current 更新为 next。
     * 如果成功更新，即当前值为 current，则将其更新为 next，相当于实现了自增的效果。
     * 如果更新失败，说明有其他线程在此期间修改了值，因此会继续循环直到成功为止。
     */
    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("*******第几次访问次数，次数next：" + next);
        return next;
    }

    /**
     * 负载均衡算法：rest接口第几次请求数 % 服务器集群总数量 = 实际调用服务器位置下标，每次服务重启动后rest接口计数从1开始。
     */
    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
