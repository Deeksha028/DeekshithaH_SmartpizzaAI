package com.wipro.smartpizza.service;
 
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.wipro.smartpizza.dto.OrderRequestDTO;
import com.wipro.smartpizza.dto.OrderResponseDTO;
import com.wipro.smartpizza.entity.Cart;
import com.wipro.smartpizza.entity.PizzaOrder;
import com.wipro.smartpizza.entity.User;
import com.wipro.smartpizza.repository.CartRepository;
import com.wipro.smartpizza.repository.OrderRepository;
import com.wipro.smartpizza.repository.UserRepository;
 
@Service
public class OrderServiceImpl implements OrderService {
 
    @Autowired
    private OrderRepository repository;
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private CartService cartService;
    
    @Autowired
    private UserRepository userRepository;

 //placeOrder()
    @Override
    public OrderResponseDTO placeOrder(OrderRequestDTO dto) {
 
    	System.out.println("NAME =" +dto.getCustomerName());
    	System.out.println("EMAIL =" +dto.getCustomerEmail());
    	
    	List<Cart> cartItems =
    	        cartRepository.findByUserEmail(dto.getCustomerEmail());
    	 
    	String pizzaNames = cartItems.stream()
    	        .map(Cart::getPizzaName)
    	        .collect(Collectors.joining(", "));
    	PizzaOrder order = PizzaOrder.builder()
 
    		    .customerName(dto.getCustomerName())
    		    .customerEmail(dto.getCustomerEmail())
    		    .pizzaName(pizzaNames)
    		    .totalAmount(dto.getTotalAmount())
    		    .status("DELIVERED")
    		    .paymentStatus("DONE")
    		    .deliveryStatus("DELIVERED")
    		    .etaMinutes(0)
    		    .deliveryPerson("-")
    		    .createdAt(LocalDateTime.now())
    		    .build();
 
        PizzaOrder saved = repository.save(order);
        cartService.clearCart(dto.getCustomerEmail());
        
//        cartRepository.deleteByUserEmail(dto.getCustomerEmail());
 
        return OrderResponseDTO.builder()
                .id(saved.getId())
                .customerName(saved.getCustomerName())
                .customerEmail(saved.getCustomerEmail())
                .totalAmount(saved.getTotalAmount())
                .status(saved.getStatus())
                //Audit fields
                .createdAt(saved.getCreatedAt())
                .updatedAt(saved.getUpdatedAt())
                .paymentStatus(order.getPaymentStatus())
                .deliveryStatus(order.getDeliveryStatus())
                .etaMinutes(order.getEtaMinutes())
                .deliveryPerson(order.getDeliveryPerson())
                .build();
    }
 //getAllOrders()
    @Override
    public List<OrderResponseDTO> getAllOrders() {
 
        return repository.findAll()
                .stream()
                .map(order -> OrderResponseDTO.builder()
                        .id(order.getId())
                        .customerName(order.getCustomerName())
                        .customerEmail(order.getCustomerEmail())
                        .totalAmount(order.getTotalAmount())
                        .status(order.getStatus())
                        .createdAt(order.getCreatedAt())
                        .updatedAt(order.getUpdatedAt())
                        .paymentStatus(order.getPaymentStatus())
                        .deliveryStatus(order.getDeliveryStatus())
                        .etaMinutes(order.getEtaMinutes())
                        .deliveryPerson(order.getDeliveryPerson())
                        .build())
                .collect(Collectors.toList());
    }
 //getOrderById()
    @Override
    public OrderResponseDTO getOrderById(Long id) {
 
        PizzaOrder order = repository.findById(id).orElseThrow(() -> new RuntimeException("Order not found with id:"+id));
 
        return OrderResponseDTO.builder()
                .id(order.getId())
                .customerName(order.getCustomerName())
                .customerEmail(order.getCustomerEmail())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .paymentStatus(order.getPaymentStatus())
                .deliveryStatus(order.getDeliveryStatus())
                .etaMinutes(order.getEtaMinutes())
                .deliveryPerson(order.getDeliveryPerson())
                .build();
    }
    //getOrdersByEmail()
    @Override
    public List<OrderResponseDTO> getOrdersByEmail(String email) {
     
    	return repository.findByCustomerEmail(email)
    		    .stream()
    		    .map(order -> OrderResponseDTO.builder()
    		        .id(order.getId())
    		        .customerName(order.getCustomerName())
    		        .customerEmail(order.getCustomerEmail())
    		        .totalAmount(order.getTotalAmount())
    		        .status(order.getStatus())
    		 
    		        .paymentStatus(order.getPaymentStatus())
    		        .deliveryStatus(order.getDeliveryStatus())
    		        .etaMinutes(order.getEtaMinutes())
    		        .deliveryPerson(order.getDeliveryPerson())
    		 
    		        .createdAt(order.getCreatedAt())
    		        .updatedAt(order.getUpdatedAt())
    		        .build())
    		    .collect(Collectors.toList());
    }
    //cancelOrder()
    @Override
    public OrderResponseDTO cancelOrder(Long id) {
     
        PizzaOrder order = repository.findById(id)
                .orElseThrow();
     
        order.setStatus("CANCELLED");
        order.setUpdatedAt(LocalDateTime.now());
     
        PizzaOrder saved = repository.save(order);
     
        return OrderResponseDTO.builder()
                .id(saved.getId())
                .customerName(saved.getCustomerName())
                .customerEmail(saved.getCustomerEmail())
                .totalAmount(saved.getTotalAmount())
                .status(saved.getStatus())
                .createdAt(saved.getCreatedAt())
                .updatedAt(saved.getUpdatedAt())
                .paymentStatus(order.getPaymentStatus())
                .deliveryStatus(order.getDeliveryStatus())
                .etaMinutes(order.getEtaMinutes())
                .deliveryPerson(order.getDeliveryPerson())
                .build();
    }
    
    //updateOrderStatus()
    @Override
    public OrderResponseDTO updateOrderStatus(Long id, String status) {
     
        PizzaOrder order = repository.findById(id)
                .orElseThrow();
     
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
     
        PizzaOrder saved = repository.save(order);
     
        return OrderResponseDTO.builder()
                .id(saved.getId())
                .customerName(saved.getCustomerName())
                .customerEmail(saved.getCustomerEmail())
                .totalAmount(saved.getTotalAmount())
                .status(saved.getStatus())
                .createdAt(saved.getCreatedAt())
                .updatedAt(saved.getUpdatedAt())
                .paymentStatus(order.getPaymentStatus())
                .deliveryStatus(order.getDeliveryStatus())
                .etaMinutes(order.getEtaMinutes())
                .deliveryPerson(order.getDeliveryPerson())
                .build();
    }
    
    //getOrdersByUser
    @Override
    public List<OrderResponseDTO> getOrdersByUser(Long userId) {
     
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
     
        return user.getOrders()
                .stream()
                .map(order -> OrderResponseDTO.builder()
                        .id(order.getId())
                        .customerName(order.getCustomerName())
                        .customerEmail(order.getCustomerEmail())
                        .totalAmount(order.getTotalAmount())
                        .status(order.getStatus())
                        .createdAt(order.getCreatedAt())
                        .updatedAt(order.getUpdatedAt())
                        .paymentStatus(order.getPaymentStatus())
                        .deliveryStatus(order.getDeliveryStatus())
                        .etaMinutes(order.getEtaMinutes())
                        .deliveryPerson(order.getDeliveryPerson())
                        .build())
                .toList();
    }
     
}