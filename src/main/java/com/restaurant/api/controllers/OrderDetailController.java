package com.restaurant.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.api.models.OrderDetail;
import com.restaurant.api.repositories.OrderDetailRepository;

@RestController
@CrossOrigin
@RequestMapping(value = "api/orderdetails")

public class OrderDetailController {

	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	
	@GetMapping(value = "/{id}")
	public List<OrderDetail> getAll(@PathVariable("id")Long id){
		List<Long> ids = new ArrayList<Long>();
		ids.add(id);
		
		return orderDetailRepository.findAllById(ids);
	}
	
}
