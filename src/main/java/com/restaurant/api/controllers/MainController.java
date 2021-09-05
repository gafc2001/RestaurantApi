package com.restaurant.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class MainController {
	
	@RequestMapping(value = "/")
	@ResponseBody
	public String index() {
		return "Restaurant Api";
	}
}
