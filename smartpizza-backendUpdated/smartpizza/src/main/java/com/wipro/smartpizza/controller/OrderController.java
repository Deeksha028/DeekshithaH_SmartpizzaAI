package com.wipro.smartpizza.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.smartpizza.dto.OrderRequestDTO;
import com.wipro.smartpizza.dto.OrderResponseDTO;
import com.wipro.smartpizza.dto.OrderStatusDTO;
import com.wipro.smartpizza.service.OrderService;

 
@RestController
@RequestMapping("/api/orders")
public class OrderController {
 
    @Autowired
    private OrderService service;
 
    @PostMapping
    public OrderResponseDTO placeOrder(
            @RequestBody OrderRequestDTO dto) {
 
        return service.placeOrder(dto);
    }
 
    @GetMapping
    public List<OrderResponseDTO> getAllOrders() {
 
        return service.getAllOrders();
    }
 
    @GetMapping("/{id}")
    public OrderResponseDTO getOrderById(
            @PathVariable Long id) {
 
        return service.getOrderById(id);
    }
    
    @GetMapping("/user/{email}")
    public List<OrderResponseDTO> getOrdersByEmail(@PathVariable String email){
    	return service.getOrdersByEmail(email);
    }
    @PutMapping("/cancel/{id}")
    public OrderResponseDTO cancelOrder(@PathVariable Long id) {
    	return service.cancelOrder(id);
    }
    
    @PutMapping("/{id}/status")
    public OrderResponseDTO updateOrderStatus(@PathVariable Long id,@RequestBody OrderStatusDTO dto) {
    	//System.out.println("STATUS RECEIVED = " + dto.getStatus());
        return service.updateOrderStatus(id,dto.getStatus());
    }
    @GetMapping("/userid/{userId}")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByUser(
            @PathVariable Long userId) {
     
        return ResponseEntity.ok(service.getOrdersByUser(userId));
    }
}