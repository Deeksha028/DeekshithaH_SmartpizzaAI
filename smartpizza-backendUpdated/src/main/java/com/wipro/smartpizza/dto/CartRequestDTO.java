package com.wipro.smartpizza.dto;
 
 

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
 
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartRequestDTO {

 
    private Long pizzaId;
 
    private Integer quantity;
}