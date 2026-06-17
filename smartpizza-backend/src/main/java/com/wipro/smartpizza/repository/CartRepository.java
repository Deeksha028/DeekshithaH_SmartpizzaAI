package com.wipro.smartpizza.repository;
 
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.smartpizza.entity.Cart;
 
public interface CartRepository extends JpaRepository<Cart, Long> {
 
    List<Cart> findByUserEmail(String email);
    
    Optional<Cart> findByUserEmailAndPizzaId(String userEmail,Long pizzaId);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM Cart c WHERE c.userEmail = :email")
    void deleteByUserEmail(@Param("email") String email);
}