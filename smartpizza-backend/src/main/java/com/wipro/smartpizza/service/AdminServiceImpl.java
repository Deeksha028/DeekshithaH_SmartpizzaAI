package com.wipro.smartpizza.service;
 
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.smartpizza.dto.AdminAnalyticsDTO;
import com.wipro.smartpizza.dto.CustomerTrendDTO;
import com.wipro.smartpizza.dto.DeliveryPerformanceDTO;
import com.wipro.smartpizza.dto.OrderHeatmapDTO;
import com.wipro.smartpizza.entity.Delivery;
import com.wipro.smartpizza.entity.PizzaOrder;
import com.wipro.smartpizza.entity.Role;
import com.wipro.smartpizza.entity.User;
import com.wipro.smartpizza.repository.CartRepository;
import com.wipro.smartpizza.repository.DeliveryRepository;
import com.wipro.smartpizza.repository.OrderRepository;
import com.wipro.smartpizza.repository.PaymentRepository;
import com.wipro.smartpizza.repository.UserRepository;
 
@Service
public class AdminServiceImpl implements AdminService {
 
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private UserRepository userRepository;
 
    @Autowired
    private CartRepository cartRepository;
 
    @Autowired
    private PaymentRepository paymentRepository;
 
    @Autowired
    private DeliveryRepository deliveryRepository;
 
    @Override
    public AdminAnalyticsDTO getAnalytics() {
    	
    	long totalUsers = userRepository.count();
 
        long activeCustomers = cartRepository.findAll()
                        .stream()
                        .map(cart -> cart.getUserEmail())
                        .distinct()
                        .count();
        Long deliveredOrders =
                deliveryRepository.countDelivered();
         
        Long pendingDeliveries =
                deliveryRepository.countPending();
        Double revenue =
                paymentRepository.getTotalRevenue();
        Double totalTax =
                revenue * 0.18;
        
        String topCustomer = orderRepository.findTopCustomer();
        
        long placedOrders =
                orderRepository.countPlacedOrders();
         
        long cancelledOrders =
                orderRepository.countCancelledOrders();
        
        long couponsUsed =
                orderRepository.countCouponUsage();
        
        Double averageOrderValue = 0.0;
        
        if(orderRepository.getOrderCount() > 0) {
            averageOrderValue =
                paymentRepository.getTotalRevenue()
                / orderRepository.getOrderCount();
        }
        
        Long totalCustomers =
                userRepository.countByRole(Role.CUSTOMER);
         
        Long totalAdmins =
                userRepository.countByRole(Role.ADMIN);
         
        Long totalDeliveryUsers =
                userRepository.countByRole(Role.DELIVERY);
        
        Long todayOrders = orderRepository.countTodayOrders();
        
        Long weeklyOrders = orderRepository.countWeeklyOrders();
        
        Long monthlyOrders = orderRepository.countMonthlyOrders();
        
        Double todayRevenue =
                paymentRepository.getTodayRevenue();
         
        Double weeklyRevenue =
                paymentRepository.getRevenueAfter(
                    LocalDateTime.now().minusDays(7)
                );
         
        Double monthlyRevenue =
                paymentRepository.getRevenueAfter(
                    LocalDateTime.now().minusDays(30)
                );
 
        String topPizza =
        	    orderRepository.findAll()
        	        .stream()
        	        .filter(order -> order.getPizzaName() != null)
        	        .collect(Collectors.groupingBy(
        	            PizzaOrder::getPizzaName,
        	            Collectors.counting()))
        	        .entrySet()
        	        .stream()
        	        .max(Map.Entry.comparingByValue())
        	        .map(Map.Entry::getKey)
        	        .orElse("No Orders");
 
        return AdminAnalyticsDTO.builder()
                .totalOrders(orderRepository.count())
                .totalPayments(paymentRepository.count())
                .totalDeliveries(deliveryRepository.count())
                .totalRevenue(paymentRepository.getTotalRevenue())
                .activeCustomers(activeCustomers)
                .topPizza(topPizza)
                .totalUsers(totalUsers)
                .deliveredOrders(deliveredOrders)
                .pendingDeliveries(pendingDeliveries)
                .totalTax(totalTax)
                .topCustomer(topCustomer)
                .placedOrders(placedOrders)
                .cancelledOrders(cancelledOrders)
                .couponsUsed(couponsUsed)
                .averageOrderValue(averageOrderValue)
                .totalCustomers(totalCustomers)
                .totalAdmins(totalAdmins)
                .totalDeliveryUsers(totalDeliveryUsers)
                .todayOrders(todayOrders)
                .weeklyOrders(weeklyOrders)
                .monthlyOrders(monthlyOrders)
                .todayRevenue(todayRevenue)
                .weeklyRevenue(weeklyRevenue)
                .monthlyRevenue(monthlyRevenue)
                .build();
    }
    
    @Override
    public CustomerTrendDTO getCustomerTrends() {
     
        Long totalCustomers = userRepository.count();
     
        Long totalOrders = orderRepository.count();
     
        String topCustomer = userRepository.findAll()
                .stream()
                .findFirst()
                .map(User::getName)
                .orElse("N/A");
     
        return CustomerTrendDTO.builder()
                .totalCustomers(totalCustomers)
                .totalOrders(totalOrders)
                .topCustomer(topCustomer)
                .topCustomerOrders(0L)
                .build();
    }
    @Override
    public DeliveryPerformanceDTO getDeliveryPerformance() {
     
        List<Delivery> deliveries = deliveryRepository.findAll();
     
        Long total = (long) deliveries.size();
     
        Long completed = deliveries.stream()
                .filter(d -> "DELIVERED".equalsIgnoreCase(d.getStatus()))
                .count();
     
        Double avgEta = deliveries.stream()
                .mapToInt(Delivery::getEtaMinutes)
                .average()
                .orElse(0);
     
        return DeliveryPerformanceDTO.builder()
                .totalDeliveries(total)
                .completedDeliveries(completed)
                .averageEta(avgEta)
                .build();
    }
    @Override
    public List<OrderHeatmapDTO> getOrderHeatmap() {
     
        return List.of(
                new OrderHeatmapDTO("Bangalore", 40L),
                new OrderHeatmapDTO("Hyderabad", 25L),
                new OrderHeatmapDTO("Chennai", 18L),
                new OrderHeatmapDTO("Pune", 12L)
        );
    }
}