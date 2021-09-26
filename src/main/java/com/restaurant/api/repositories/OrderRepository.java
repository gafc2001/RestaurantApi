package com.restaurant.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.api.models.OrderUser;

public interface OrderRepository extends JpaRepository<OrderUser, Long>{

}
