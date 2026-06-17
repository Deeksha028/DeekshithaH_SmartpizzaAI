package com.wipro.smartpizza.service;
 
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.smartpizza.dto.CartRequestDTO;
import com.wipro.smartpizza.dto.CartResponseDTO;
import com.wipro.smartpizza.entity.Cart;
import com.wipro.smartpizza.entity.Pizza;
import com.wipro.smartpizza.repository.CartRepository;
import com.wipro.smartpizza.repository.PizzaRepository;

import lombok.RequiredArgsConstructor;
 
@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {
 
    private final CartRepository cartRepository;
    private final PizzaRepository pizzaRepository;
 
    @Override
    public CartResponseDTO addToCart(CartRequestDTO dto, String email) {
 
        Pizza pizza = pizzaRepository.findById(dto.getPizzaId())
                .orElseThrow(() -> new RuntimeException("Pizza not found"));
 
        Optional<Cart> existingCart =
                cartRepository.findByUserEmailAndPizzaId(
                        email,
                        dto.getPizzaId());
 
        Cart saved;
 
        if (existingCart.isPresent()) {
 
            Cart cart = existingCart.get();
 
            int newQty = cart.getQuantity() + dto.getQuantity();
 
            cart.setQuantity(newQty);
 
            cart.setTotalPrice(
                    pizza.getPrice() * newQty
            );
 
            saved = cartRepository.save(cart);
 
        } else {
 
            Cart cart = Cart.builder()
                    .userEmail(email)
                    .pizzaId(pizza.getId())
                    .pizzaName(pizza.getName())
                    .quantity(dto.getQuantity())
                    .price(pizza.getPrice())
                    .totalPrice(
                            pizza.getPrice() * dto.getQuantity()
                    )
                    .build();
 
            saved = cartRepository.save(cart);
        }
 
        return CartResponseDTO.builder()
                .id(saved.getId())
                .userEmail(saved.getUserEmail())
                .pizzaName(saved.getPizzaName())
                .quantity(saved.getQuantity())
                .totalPrice(saved.getTotalPrice())
                .build();
    }
 
    @Override
    public List<CartResponseDTO> getCart(String email) {
 
        return cartRepository.findByUserEmail(email)
                .stream()
                .map(cart -> CartResponseDTO.builder()
                        .id(cart.getId())
                        .userEmail(cart.getUserEmail())
                        .pizzaName(cart.getPizzaName())
                        .quantity(cart.getQuantity())
                        .totalPrice(cart.getTotalPrice())
                        .build())
                .collect(Collectors.toList());
    }
 
    @Override
    public void removeFromCart(Long id) {
        cartRepository.deleteById(id);
    }
    
    @Override
    public void clearCart(String email) {
    	cartRepository.deleteByUserEmail(email);
    }
}
 