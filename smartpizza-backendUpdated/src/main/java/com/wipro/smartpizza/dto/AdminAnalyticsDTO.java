package com.wipro.smartpizza.dto;
 
import lombok.Builder;
import lombok.Data;
 
@Data
@Builder
public class AdminAnalyticsDTO {
 
    private Long totalOrders;
    private Long totalPayments; 
    private Long totalDeliveries;
    private Double totalRevenue;   
    private long activeCustomers;    
    private String topPizza;    
    private Long totalCustomers;    
    private Long totalAdmins;     
    private Long totalDeliveryUsers;    
    private Long todayOrders;    
    private Long weeklyOrders;    
    private Long monthlyOrders;    
    private Double todayRevenue;    
    private Double weeklyRevenue;    
    private Double monthlyRevenue;    
    private Long deliveredOrders;    
    private long placedOrders;    
    private long cancelledOrders;    
    private long couponsUsed;    
    private Double averageOrderValue;    
    private Long pendingDeliveries;    
    private Long totalUsers;    
    private Double totalTax;    
    private String topCustomer;
}