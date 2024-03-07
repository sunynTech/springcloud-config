package com.ning.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author sunyuning
 * @date 2024/3/7 09:19
 * @description: PaymentFallbackService
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    /**
     * 调用的是paymentInfo_OK方法，没有加任何注解
     * @param id
     * @return
     */
    @Override
    public String paymentInfo_OK(Integer id) {
        return "------PaymentFallbackService fall back paymentInfo_OK方法，o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "------PaymentFallbackService fall back paymentInfo_TimeOut方法，o(╥﹏╥)o";
    }
}
