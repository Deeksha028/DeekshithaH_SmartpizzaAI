package com.wipro.smartpizza.dto;
 
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
 
@Data
@Builder
public class OrderResponseDTO {
 
    private Long id;
 
    private String customerName;
 
    private String customerEmail;
 
    private Double totalAmount;
 
    private String status;
 
    private String paymentStatus;
 
    private String deliveryStatus;
 
    private Integer etaMinutes;
 
    private String deliveryPerson;
 
    private LocalDateTime createdAt;
 
    private LocalDateTime updatedAt;
}