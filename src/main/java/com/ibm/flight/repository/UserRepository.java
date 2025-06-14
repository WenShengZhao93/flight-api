package com.ibm.flight.repository;

import com.ibm.flight.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);

	User findByEmail(String email);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);
}