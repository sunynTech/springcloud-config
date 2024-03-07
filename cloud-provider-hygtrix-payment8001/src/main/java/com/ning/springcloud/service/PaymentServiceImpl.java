package com.ning.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author sunyuning
 * @date 2024/3/6 13:23
 * @description: PaymentServiceImpl
 */
@Service
public class PaymentServiceImpl {
    /**
     * 这个是正常访问，肯定不会报错的方法
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池： " + Thread.currentThread().getName() + "   paymentInfo_OK,id： " + id + "\t" + "(｡･∀･)ﾉﾞ";
    }

    /**
     * 故意报错的方法，超时
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler"/*指定善后方法名*/, commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "13000"/*超时时间设置为3秒*/)
    })
    public String paymentInfo_TimeOut(Integer id) {
        /*故意制造两种异常:
         * int age = 10/0，计算异常
         * 我们能接受3秒钟，它运行5秒钟，超时异常。
         * 当前服务不可用了，做服务降级，兜底的方案都是paymentInfo_TimeOutHandler
         * */
//        int age = 10 / 0;
//        return String.valueOf(age);
        int timeNumber = 6;
        //暂停5秒钟线程
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： " + Thread.currentThread().getName() + "   paymentInfo_TimeOut,id： " + id + "\t" + "(╯°口°)╯︵ ┻━┻" + "   耗时： " + timeNumber + "秒";
    }

    /**
     * 用来善后的方法
     */
    public String paymentInfo_TimeOutHandler(Integer id) {
        return "╥﹏╥调用支付接口超时或异常：\t" + "\t当前线程名字" + Thread.currentThread().getName();
    }

    //***********服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if(id < 0) {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }
}
