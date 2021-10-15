package com.restaurant.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.restaurant.api.models.Payment;
import com.restaurant.api.repositories.PaymentRepository;

@RestController
@CrossOrigin
@RequestMapping(value = "api/payment")
public class PaymentController {

	@Autowired
	PaymentRepository paymentRepository;
	
	@GetMapping
	public List<Payment> getAll(){
		return paymentRepository.findAll();
	}
	@GetMapping(value = "/{id}")
	public Payment getById(@PathVariable("id") Long id) {
		return paymentRepository.findById(id).get();
	}
	
	@PostMapping
	public Payment create(@RequestBody Payment requestPayment) {
		return paymentRepository.save(requestPayment);
	}
}
