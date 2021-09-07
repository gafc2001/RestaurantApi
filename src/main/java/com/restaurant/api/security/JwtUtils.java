package com.restaurant.api.security;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;

public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	
	
	@Value("${com.restaurant.api.jwtSecret}")
	private String jwtSecret;
	
	@Value("${com.restaurant.api.jwtExpirationMs}")
	private String jwtExpirationMs;
}
