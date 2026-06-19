package com.wipro.smartpizza.service;
 
import java.util.List;

import com.wipro.smartpizza.dto.OrderRequestDTO;
import com.wipro.smartpizza.dto.OrderResponseDTO;
 
public interface OrderService {
 
    OrderResponseDTO placeOrder(OrderRequestDTO dto);
 
    List<OrderResponseDTO> getAllOrders();
 
    OrderResponseDTO getOrderById(Long id);
    
    List<OrderResponseDTO> getOrdersByEmail(String email);
    
    OrderResponseDTO cancelOrder(Long id);
    
    OrderResponseDTO updateOrderStatus(Long id,String status);
    
    List<OrderResponseDTO> getOrdersByUser(Long userId);
}