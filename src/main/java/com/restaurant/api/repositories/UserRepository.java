package com.restaurant.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.api.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String nameUser);

	Boolean existsByUsername(String nameUser);

	Boolean existsByEmail(String email);
}
