package com.wipro.smartpizza.repository;
 
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wipro.smartpizza.entity.Delivery;
 
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
 
    Optional<Delivery> findByOrder_Id(Long orderId);
    
    @Query("SELECT COUNT(d) FROM Delivery d WHERE d.status='DELIVERED'")
    Long countDelivered();
    
    @Query("SELECT COUNT(d) FROM Delivery d WHERE d.status='PENDING'")
    Long countPending();
    
//    @Query("SELECT COUNT(d) FROM Delivery d")
//    Long getDeliveryCount();
//    @Query("SELECT d FROM Delivery d WHERE d.orderId = :orderId")
//    Optional<Delivery> findByOrderId(@Param("orderId") Long orderId);
 
}