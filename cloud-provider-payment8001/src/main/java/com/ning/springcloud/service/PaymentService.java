package com.ning.springcloud.service;

import com.ning.springcloud.entities.Payment;

/**
 * @author sunyuning
 * @date 2024/3/3 16:12
 * @description: PaymentService
 */
public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(Long id);
}
