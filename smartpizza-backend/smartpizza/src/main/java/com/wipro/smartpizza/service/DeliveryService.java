package com.wipro.smartpizza.service;
 
import java.util.List;

import com.wipro.smartpizza.dto.DeliveryDTO;
import com.wipro.smartpizza.dto.RouteResponseDTO;
 
public interface DeliveryService {
 
    DeliveryDTO createDelivery(DeliveryDTO dto);
 
    DeliveryDTO getDeliveryById(Long id);
 
    DeliveryDTO getDeliveryByOrderId(Long orderId);
 
    List<DeliveryDTO> getAllDeliveries();
 
    DeliveryDTO updateDeliveryStatus(Long id, String status);
    
    RouteResponseDTO optimizeRoute(Long deliveryId);
 
}