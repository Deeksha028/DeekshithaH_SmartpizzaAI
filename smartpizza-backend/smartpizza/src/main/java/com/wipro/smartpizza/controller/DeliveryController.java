package com.wipro.smartpizza.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.smartpizza.dto.DeliveryDTO;
import com.wipro.smartpizza.dto.RouteResponseDTO;
import com.wipro.smartpizza.service.DeliveryService;
 
@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {
 
    @Autowired
    private DeliveryService service;
 
    @PostMapping
    public DeliveryDTO create(@RequestBody DeliveryDTO dto) {
        return service.createDelivery(dto);
    }
 
    @GetMapping("/{id}")
    public DeliveryDTO getById(@PathVariable Long id) {
        return service.getDeliveryById(id);
    }
 
    @GetMapping("/order/{orderId}")
    public DeliveryDTO getByOrderId(@PathVariable Long orderId) {
        return service.getDeliveryByOrderId(orderId);
    }
 
    @GetMapping
    public List<DeliveryDTO> getAll() {
        return service.getAllDeliveries();
    }
 
    @PutMapping("/{id}/status")
    public DeliveryDTO updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
 
        return service.updateDeliveryStatus(id, status);
    }
    @GetMapping("/route/{id}")
    public RouteResponseDTO optimizeRoute(
            @PathVariable Long id) {
     
        return service.optimizeRoute(id);
    }
}