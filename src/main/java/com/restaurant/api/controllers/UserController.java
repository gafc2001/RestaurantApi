package com.restaurant.api.controllers;

import java.util.List;

import com.restaurant.api.payload.response.SummaryReport;
import com.restaurant.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.restaurant.api.models.User;

@Controller
@CrossOrigin
@RequestMapping(value = "/api/users")
public class UserController{

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public List<User> getAll(){
		return userRepository.findAll();
	}

	@GetMapping(value = "/{id}")
	public User getById(@PathVariable("id")Long id){
		return userRepository.findById(id).get();
	}
	@PatchMapping(value = "/{id}")
	public User updateUser(@PathVariable("id")Long id,@RequestBody User user){
		user.setIdUser(id);
		return userRepository.save(user);
	}

	@GetMapping(value = "/summary")
	public SummaryReport getTotalUsers(){
		return null;
	}
	

	
}
