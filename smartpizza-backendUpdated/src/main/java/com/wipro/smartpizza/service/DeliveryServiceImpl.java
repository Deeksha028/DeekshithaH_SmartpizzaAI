package com.wipro.smartpizza.service;
 
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.smartpizza.dto.DeliveryDTO;
import com.wipro.smartpizza.dto.RouteResponseDTO;
import com.wipro.smartpizza.entity.Delivery;
import com.wipro.smartpizza.entity.PizzaOrder;
import com.wipro.smartpizza.repository.DeliveryRepository;
import com.wipro.smartpizza.repository.OrderRepository;
 
@Service
public class DeliveryServiceImpl implements DeliveryService {
 
    @Autowired
    private DeliveryRepository repository;
    
    @Autowired
    private OrderRepository orderRepository;
 
    @Override
    public DeliveryDTO createDelivery(DeliveryDTO dto) {
 
    	PizzaOrder order = orderRepository.findById(dto.getOrderId())
    	        .orElseThrow(() -> new RuntimeException("Order not found"));
    	
    	
    	 
    	Delivery delivery = Delivery.builder()
    	        .order(order)
    	        .deliveryPerson(dto.getDeliveryPerson())
    	        .currentLocation(dto.getCurrentLocation())
    	        .etaMinutes(dto.getEtaMinutes())
    	        .status(dto.getStatus())
    	        .build();
 
        Delivery saved = repository.save(delivery);
 
        return map(saved);
    }
 
    @Override
    public DeliveryDTO getDeliveryById(Long id) {
 
        Delivery delivery = repository.findById(id).orElseThrow();
 
        return map(delivery);
    }
 
    @Override
    public DeliveryDTO getDeliveryByOrderId(Long orderId) {
 
        Delivery delivery = repository.findByOrder_Id(orderId).orElseThrow(() -> new RuntimeException("Delivery not found"));
 
        return map(delivery);
    }
 
    @Override
    public List<DeliveryDTO> getAllDeliveries() {
 
        return repository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
 
    @Override
    public DeliveryDTO updateDeliveryStatus(Long id, String status) {
 
        Delivery delivery = repository.findById(id).orElseThrow();
 
        delivery.setStatus(status);
 
        Delivery updated = repository.save(delivery);
 
        return map(updated);
    }
 
    private DeliveryDTO map(Delivery d) {
 
        return DeliveryDTO.builder()
                .id(d.getId())
                .orderId(d.getOrder().getId())
                .deliveryPerson(d.getDeliveryPerson())
                .currentLocation(d.getCurrentLocation())
                .etaMinutes(d.getEtaMinutes())
                .status(d.getStatus())
                .build();
    }
    @Override
    public RouteResponseDTO optimizeRoute(Long deliveryId) {
     
        Delivery delivery =
                repository.findById(deliveryId)
                .orElseThrow();
     
        String route;
     
        if(delivery.getEtaMinutes() <= 20)
            route = "Shortest Route";
        else
            route = "Highway Route";
     
        return new RouteResponseDTO(
                deliveryId,
                route
        );
    }
}