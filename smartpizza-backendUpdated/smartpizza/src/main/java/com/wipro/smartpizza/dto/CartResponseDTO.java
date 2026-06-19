package com.wipro.smartpizza.dto;
 

import lombok.Builder;
import lombok.Data;

 
@Data
@Builder
public class CartResponseDTO {
 
    private Long id;
 
    private String userEmail;
 
    private String pizzaName;
 
    private Integer quantity;
 
    private Double totalPrice;
}