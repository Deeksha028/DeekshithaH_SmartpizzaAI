package com.wipro.smartpizza.dto;
 
import lombok.Builder;
import lombok.Data;
 
@Data
@Builder
public class CustomerTrendDTO {
 
    private Long totalCustomers;
 
    private Long totalOrders;
 
    private String topCustomer;
 
    private Long topCustomerOrders;
}