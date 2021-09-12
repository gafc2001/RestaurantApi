package com.restaurant.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.api.models.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod,Long>{
	
}
