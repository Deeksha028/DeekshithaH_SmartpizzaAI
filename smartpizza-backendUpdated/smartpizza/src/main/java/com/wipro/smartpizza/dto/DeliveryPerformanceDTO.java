package com.wipro.smartpizza.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryPerformanceDTO {
 
    private Long totalDeliveries;
 
    private Long completedDeliveries;
 
    private Double averageEta;
}
