package com.restaurant.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.api.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
