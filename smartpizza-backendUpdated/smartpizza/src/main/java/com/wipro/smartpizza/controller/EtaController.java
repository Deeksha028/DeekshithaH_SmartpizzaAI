package com.wipro.smartpizza.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.smartpizza.dto.DeliveryDTO;
import com.wipro.smartpizza.service.DeliveryService;
 
@RestController
@RequestMapping("/api/delivery")
public class EtaController {
 
//    @Autowired
//    private EtaService etaService;
	
	@Autowired
	private DeliveryService deliveryService;
 
    @GetMapping("/eta/{orderId}")
    public DeliveryDTO getEta(@PathVariable Long orderId) {
        return deliveryService.getDeliveryByOrderId(orderId);
    }
}