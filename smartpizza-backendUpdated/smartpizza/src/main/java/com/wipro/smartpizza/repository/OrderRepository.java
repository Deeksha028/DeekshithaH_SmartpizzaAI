package com.wipro.smartpizza.repository;
 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wipro.smartpizza.entity.PizzaOrder;
 
public interface OrderRepository
        extends JpaRepository<PizzaOrder, Long> {
	
	List<PizzaOrder> findByCustomerEmail(String email);
	
	@Query("""
			SELECT o.customerEmail
			FROM PizzaOrder o
			GROUP BY o.customerEmail
			ORDER BY COUNT(o) DESC
			LIMIT 1
			""")
			String findTopCustomer();
	
	@Query("SELECT COUNT(o) FROM PizzaOrder o WHERE o.status='PLACED'")
	Long countPlacedOrders();
	 
	@Query("SELECT COUNT(o) FROM PizzaOrder o WHERE o.status='CANCELLED'")
	Long countCancelledOrders();
	
	@Query("""
			SELECT COUNT(o)
			FROM PizzaOrder o
			WHERE o.coupon IS NOT NULL
			""")
			Long countCouponUsage();
	
	@Query("SELECT COUNT(o) FROM PizzaOrder o")
	Long getOrderCount();
	
	@Query( value = "SELECT COUNT(*) FROM orders WHERE DATE(created_at)=CURDATE()", nativeQuery = true)
			Long countTodayOrders();
	
	@Query(
			value = """
			         SELECT COUNT(*)
			         FROM orders
			         WHERE created_at >= DATE_SUB(CURDATE(), INTERVAL 7 DAY)
			         """,
			nativeQuery = true
			)
			Long countWeeklyOrders();
	@Query(
			value = """
			         SELECT COUNT(*)
			         FROM orders
			         WHERE MONTH(created_at)=MONTH(CURDATE())
			         AND YEAR(created_at)=YEAR(CURDATE())
			         """,
			nativeQuery = true
			)
			Long countMonthlyOrders();

}
