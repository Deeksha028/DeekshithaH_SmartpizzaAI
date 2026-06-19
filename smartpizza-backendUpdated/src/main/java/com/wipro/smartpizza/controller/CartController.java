package com.wipro.smartpizza.controller;
 
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.smartpizza.dto.CartRequestDTO;
import com.wipro.smartpizza.dto.CartResponseDTO;
import com.wipro.smartpizza.service.CartService;

import lombok.RequiredArgsConstructor;
 
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
 
    private final CartService cartService;
    
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);
 
    
    @PostMapping
    public CartResponseDTO addToCart(@RequestBody CartRequestDTO dto, Authentication authentication) {
    	
    	String email = authentication.getName();
    	
    	logger.info("EMAIL FROM TOKEN = {}",email);
    	
 
        return cartService.addToCart(dto,email);
    }
 
    @GetMapping("/{email}")
    public List<CartResponseDTO> getCart(
            @PathVariable String email) {
 
        return cartService.getCart(email);
    }
 
    @DeleteMapping("/{id}")
    public String removeFromCart(
            @PathVariable Long id) {
 
        cartService.removeFromCart(id);
 
        return "Item removed successfully";
    }
    
    @DeleteMapping("/clear/{email}")
    public String clearCart(
            @PathVariable String email) {
     
        cartService.clearCart(email);
     
        return "Cart cleared successfully";
    }
}