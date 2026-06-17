package com.wipro.smartpizza.dto;
 
import lombok.Builder;
import lombok.Data;
 
@Data
@Builder
public class InvoiceDTO {
 
    private Long paymentId;
 
    private Long orderId;
 
    private Double amount;
 
    private Double tax;
 
    private Double finalAmount;
 
    private String paymentMethod;
 
    private String paymentStatus;
}