package com.ning.springcloud.dao;

import com.ning.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author sunyuning
 * @date 2024/3/2 22:03
 * @description: PaymentDao
 */
@Mapper
public interface PaymentDao {
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
