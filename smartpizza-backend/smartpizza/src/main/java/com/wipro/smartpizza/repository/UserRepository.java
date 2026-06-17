package com.wipro.smartpizza.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.smartpizza.entity.Role;
import com.wipro.smartpizza.entity.User;

public interface UserRepository extends JpaRepository <User,Long> {
	
	Optional <User> findByEmail(String email);
	
	Long countByRole(Role role);

}
