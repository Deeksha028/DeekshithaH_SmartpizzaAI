package com.wipro.smartpizza.service;
 
import java.util.List;

import com.wipro.smartpizza.dto.CartRequestDTO;
import com.wipro.smartpizza.dto.CartResponseDTO;
 
public interface CartService {
 
    CartResponseDTO addToCart(CartRequestDTO dto,String email);
 
    List<CartResponseDTO> getCart(String email);
 
    void removeFromCart(Long id);
    
    void clearCart(String email);
}