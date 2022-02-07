package com.restaurant.api.controllers.exceptions;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;



@ControllerAdvice
@RequestMapping("/api")
public class ExceptionHandlerController{
	
	@ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HashMap<String, String> handleNoHandlerFound(NoHandlerFoundException e, WebRequest request) {
        HashMap<String, String> response = new HashMap<>();
        response.put("status", "fail");
        response.put("message", e.getLocalizedMessage());
        return response;
    }
	
}
