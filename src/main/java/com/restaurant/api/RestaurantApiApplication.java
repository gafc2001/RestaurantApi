package com.restaurant.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.core.SpringVersion;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class RestaurantApiApplication implements CommandLineRunner{
	
	public static void main(String[] args) {
		SpringApplication.run(RestaurantApiApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		   System.out.println("version: " + SpringVersion.getVersion());
	}	


}
