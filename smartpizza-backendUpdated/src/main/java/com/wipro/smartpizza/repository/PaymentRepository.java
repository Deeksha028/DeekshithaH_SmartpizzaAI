package com.wipro.smartpizza.repository;
 
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wipro.smartpizza.entity.Payment;
 
public interface PaymentRepository
        extends JpaRepository<Payment, Long> {
	
	 @Query("SELECT COALESCE(SUM(p.amount),0) FROM Payment p")
	    Double getTotalRevenue();
	 
	 @Query("""
			 SELECT COALESCE(SUM(p.amount),0)
			 FROM Payment p
			 WHERE DATE(p.order.createdAt)=CURRENT_DATE
			 """)
			 Double getTodayRevenue();
			  
			  
			 @Query("""
			 SELECT COALESCE(SUM(p.amount),0)
			 FROM Payment p
			 WHERE p.order.createdAt >= :startDate
			 """)
			 Double getRevenueAfter(@Param("startDate") LocalDateTime startDate);
			  
	}
