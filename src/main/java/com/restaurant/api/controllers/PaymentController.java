package com.restaurant.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.restaurant.api.payload.request.PaymentRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
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

	@PostMapping("/strip")
	public String createPaymentStripe(@RequestBody PaymentRequest paymentRequest){
		Stripe.apiKey = "sk_test_51JufoqDOx3uLFoGurrcjLUA7bhhpcA9RI0e5ai1BA1fxxb9Xzqqsl8Kmlye6fnXzsn7diGYrbaIO9scTS31EofFA00MUZZ5Z9y";
		Map<String, Object> params = new HashMap<>();
		params.put("payment_method",paymentRequest.getPaymentId());
		params.put("amount",paymentRequest.getAmount());
		params.put("currency","USD");
		params.put("confirm",true);
		try {
			PaymentIntent paymentIntent = PaymentIntent.create(params);
			return paymentIntent.toJson();
		} catch (StripeException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}


}
