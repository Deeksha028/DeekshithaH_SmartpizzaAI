package com.wipro.smartpizza.dto;
 
import lombok.AllArgsConstructor;
import lombok.Data;
 
@Data
@AllArgsConstructor
public class RouteResponseDTO {
 
    private Long deliveryId;
    private String recommendedRoute;
}