package com.restaurant.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.api.models.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{

}
