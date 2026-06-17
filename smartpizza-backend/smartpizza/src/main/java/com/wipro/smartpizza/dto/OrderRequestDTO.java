package com.wipro.smartpizza.dto;
 
import lombok.Data;
 
@Data
public class OrderRequestDTO {
 
    private String customerName;
 
    private String customerEmail;
 
    private Double totalAmount;
}