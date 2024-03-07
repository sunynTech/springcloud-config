package com.ning.springcloud.service.impl;

import com.ning.springcloud.dao.PaymentDao;
import com.ning.springcloud.entities.Payment;
import com.ning.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sunyuning
 * @date 2024/3/3 16:13
 * @description: PaymentServiceImpl
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
