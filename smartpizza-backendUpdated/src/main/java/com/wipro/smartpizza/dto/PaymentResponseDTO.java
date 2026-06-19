package com.wipro.smartpizza.dto;
 
import lombok.Builder;
import lombok.Data;
 
@Data
@Builder
public class PaymentResponseDTO {
 
    private Long id;
 
    private Long orderId;
 
    private Double amount;
 
    private Double tax;
 
    private Double finalAmount;
 
    private String paymentMethod;
 
    private String paymentGateway;
 
    private String paymentStatus;
}