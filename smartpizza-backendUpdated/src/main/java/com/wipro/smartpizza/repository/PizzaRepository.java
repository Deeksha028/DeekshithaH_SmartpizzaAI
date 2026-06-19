package com.wipro.smartpizza.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.smartpizza.entity.Pizza;
 
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
 
}