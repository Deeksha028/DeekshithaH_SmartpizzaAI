package com.wipro.smartpizza.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
 
import java.util.Optional;
 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import com.wipro.smartpizza.dto.CartRequestDTO;
import com.wipro.smartpizza.dto.CartResponseDTO;
import com.wipro.smartpizza.entity.Cart;
import com.wipro.smartpizza.entity.Pizza;
import com.wipro.smartpizza.repository.CartRepository;
import com.wipro.smartpizza.repository.PizzaRepository;
 
@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {
 
    @Mock
    private CartRepository cartRepository;
 
    @Mock
    private PizzaRepository pizzaRepository;
 
    @InjectMocks
    private CartServiceImpl cartService;
 
    @Test
    void testAddToCart_NewCart() {
 
        CartRequestDTO dto = new CartRequestDTO();
        dto.setPizzaId(1L);
        dto.setQuantity(2);
 
        Pizza pizza = new Pizza();
        pizza.setId(1L);
        pizza.setName("Margherita");
        pizza.setPrice(250.0);
 
        Cart savedCart = Cart.builder()
                .id(1L)
                .userEmail("john@gmail.com")
                .pizzaId(1L)
                .pizzaName("Margherita")
                .quantity(2)
                .price(250.0)
                .totalPrice(500.0)
                .build();
 
        when(pizzaRepository.findById(1L))
                .thenReturn(Optional.of(pizza));
 
        when(cartRepository.findByUserEmailAndPizzaId("john@gmail.com", 1L))
                .thenReturn(Optional.empty());
 
        when(cartRepository.save(any(Cart.class)))
                .thenReturn(savedCart);
 
        CartResponseDTO response =
                cartService.addToCart(dto, "john@gmail.com");
 
        assertNotNull(response);
        assertEquals("john@gmail.com", response.getUserEmail());
        assertEquals("Margherita", response.getPizzaName());
        assertEquals(2, response.getQuantity());
        assertEquals(500.0, response.getTotalPrice());
    }
    
    @Test
    void testAddToCart_PizzaNotFound() {
     
        CartRequestDTO dto = new CartRequestDTO();
        dto.setPizzaId(100L);
        dto.setQuantity(1);
     
        when(pizzaRepository.findById(100L))
                .thenReturn(Optional.empty());
     
        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> cartService.addToCart(dto, "john@gmail.com"));
     
        assertEquals("Pizza not found", ex.getMessage());
    }
 
}
