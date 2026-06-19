package com.wipro.smartpizza.dto;
 
import lombok.Builder;
import lombok.Data;
 
@Data
@Builder
public class EtaResponseDTO {
 
    private Long orderId;
    private String deliveryPerson;
    private Integer etaMinutes;
    private String status;
}