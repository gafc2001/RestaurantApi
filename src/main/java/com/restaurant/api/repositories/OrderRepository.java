package com.restaurant.api.repositories;


import java.util.Date;
import java.util.List;

import com.restaurant.api.payload.response.OrderReport;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.api.models.OrderUser;

public interface OrderRepository extends JpaRepository<OrderUser, Long>{
	@Query("FROM OrderUser o WHERE o.user.idUser = ?1")
	List<OrderUser> getOrdersByUserId(Long idUser);

	@Query("SELECT od.product, SUM(od.quantity) as total FROM OrderDetail od JOIN od.orderUser o " +
			"WHERE o.createdAt >=?1 AND o.createdAt <= ?2 " +
			"GROUP BY od.product " +
			"ORDER BY total desc")
	List<List<Object>> getMostOrdered(Date range,Date current);

	/*@Query("SELECT new com.restaurant.api.payload.response.OrderReport(o.user,o.description,o.totalPrice,o.statusOrder)" +
			" FROM OrderUser o WHERE o.createdAt >= ?1 AND o.createdAt<=?2")*/
	@Query("SELECT o.user,o.description,o.totalPrice,o.statusOrder,o.createdAt" +
				  " FROM OrderUser o WHERE o.createdAt >= ?1 AND o.createdAt<=?2")
	List<OrderReport> getOrderReport(Date range, Date current);

	@Query("SELECT SUM(o.totalPrice) FROM OrderUser o WHERE o.createdAt >= ?1 AND o.createdAt<=?2")
	public Long getTotalSummaryReport(Date range,Date current);
}
