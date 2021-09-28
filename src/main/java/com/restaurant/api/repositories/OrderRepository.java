package com.restaurant.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.api.models.OrderUser;

public interface OrderRepository extends JpaRepository<OrderUser, Long>{
	@Query("FROM OrderUser o WHERE o.user.idUser = ?1")
	List<OrderUser> getOrdersByUserId(Long idUser);
	
}
