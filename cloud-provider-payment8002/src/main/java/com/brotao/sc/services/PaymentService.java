package com.brotao.sc.services;


import com.brotao.sc.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService
{
	int create(Payment payment);

	Payment getPaymentById(Long id);
}

