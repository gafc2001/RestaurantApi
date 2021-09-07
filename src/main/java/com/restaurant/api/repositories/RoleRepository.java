package com.restaurant.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.api.models.RoleUser;

public interface RoleRepository extends JpaRepository<RoleUser, Long>{

	Optional<RoleUser> findByName(String nameRole);
	
}
