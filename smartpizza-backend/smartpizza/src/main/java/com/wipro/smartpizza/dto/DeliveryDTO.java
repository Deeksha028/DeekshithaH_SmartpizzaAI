package com.wipro.smartpizza.dto;
 
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryDTO {
 
    private Long id;
 
    private Long orderId;
 
    private String deliveryPerson;
 
    private String currentLocation;
 
    private Integer etaMinutes;
 
    private String status;
}