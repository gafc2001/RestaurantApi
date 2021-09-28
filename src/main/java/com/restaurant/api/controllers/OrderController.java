package com.restaurant.api.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.api.dao.ProductDao;
import com.restaurant.api.models.OrderDetail;
import com.restaurant.api.models.OrderUser;
import com.restaurant.api.models.Payment;
import com.restaurant.api.models.PaymentMethod;
import com.restaurant.api.models.Product;
import com.restaurant.api.models.User;
import com.restaurant.api.payload.OrderDetailRequest;
import com.restaurant.api.payload.OrderRequest;
import com.restaurant.api.repositories.OrderDetailRepository;
import com.restaurant.api.repositories.OrderRepository;
import com.restaurant.api.repositories.PaymentMethodRepository;
import com.restaurant.api.repositories.PaymentRepository;
import com.restaurant.api.repositories.UserRepository;
import com.restaurant.api.service.ProductService;

@RestController
@CrossOrigin
@RequestMapping(value = "api/order")
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private PaymentMethodRepository paymentMethodRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	@GetMapping
	public List<OrderUser> getAll() {
		return orderRepository.findAll();
	}
	@GetMapping(value = "/{id}")
	public List<OrderUser> getAllById(@PathVariable("id")Long id){
		List<Long> ids = new ArrayList<Long>();
		ids.add(id);
		return orderRepository.findAllById(ids);
	}
	@GetMapping(value = "/users/{id}")
	public List<OrderUser> getOrderByUserId(@PathVariable("id")Long id){
		List<OrderUser> ordersUser= orderRepository.getOrdersByUserId(id);
		return ordersUser;
	}
	
	@PostMapping
	public OrderUser create(@RequestBody OrderRequest orderRequest) {
		User user = userRepository.findById(orderRequest.getIduser()).get();
		PaymentMethod method = paymentMethodRepository.findById(orderRequest.getPayment_method()).get();
		System.out.println(method.getNamePaymentMethod());
		String status = orderRequest.getStatus();
		Long total = orderRequest.getSubtotal();
		List<OrderDetailRequest> items = orderRequest.getOrders();
		Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();
		OrderUser order = new OrderUser(user,status,"desc",total);
		OrderUser createdOrder = orderRepository.save(order);
		for (OrderDetailRequest orderDetailRequest : items) {
			Product product = productService.findProductById(orderDetailRequest.getIdproduct());
			OrderDetail orderDetail = new OrderDetail(product,createdOrder,orderDetailRequest.getQuantity());
			OrderDetail createdOrderDetail = orderDetailRepository.save(orderDetail);
			orderDetails.add(createdOrderDetail);
		}
		Payment payment = new Payment(order, method, true);
		Set<Payment> payList = new HashSet<Payment>();
		payList.add(payment);
		paymentRepository.save(payment);
		createdOrder.setOrderDetails(orderDetails);
		createdOrder.setPayment(payList);
		return createdOrder;
	}
	
}
