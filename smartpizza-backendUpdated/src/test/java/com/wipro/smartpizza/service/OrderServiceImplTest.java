package com.wipro.smartpizza.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.wipro.smartpizza.dto.OrderRequestDTO;
import com.wipro.smartpizza.dto.OrderResponseDTO;
import com.wipro.smartpizza.entity.Cart;
import com.wipro.smartpizza.entity.PizzaOrder;
import com.wipro.smartpizza.entity.User;
import com.wipro.smartpizza.repository.CartRepository;
import com.wipro.smartpizza.repository.OrderRepository;
import com.wipro.smartpizza.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartService cartService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private OrderRequestDTO dto;
    private PizzaOrder order;

    @BeforeEach
    void setup() {

        dto = new OrderRequestDTO();
        dto.setCustomerName("Deekshitha");
        dto.setCustomerEmail("user@gmail.com");
        dto.setTotalAmount(500.0);

        order = PizzaOrder.builder()
                .id(1L)
                .customerName("Deekshitha")
                .customerEmail("user@gmail.com")
                .pizzaName("Pepperoni")
                .totalAmount(500.0)
                .status("DELIVERED")
                .paymentStatus("DONE")
                .deliveryStatus("DELIVERED")
                .etaMinutes(0)
                .deliveryPerson("-")
                .createdAt(LocalDateTime.now())
                .build();
    }

    // ✅ 1. PLACE ORDER
    @Test
    void testPlaceOrder() {

        Cart c1 = new Cart();
        c1.setPizzaName("Pepperoni");

        when(cartRepository.findByUserEmail("user@gmail.com"))
                .thenReturn(List.of(c1));

        when(orderRepository.save(any(PizzaOrder.class)))
                .thenReturn(order);

        doNothing().when(cartService).clearCart("user@gmail.com");

        OrderResponseDTO result = orderService.placeOrder(dto);

        assertNotNull(result);
        assertEquals("Deekshitha", result.getCustomerName());
        assertEquals("user@gmail.com", result.getCustomerEmail());

        verify(cartService, times(1)).clearCart("user@gmail.com");
    }

    // ✅ 2. GET ALL ORDERS
    @Test
    void testGetAllOrders() {

        when(orderRepository.findAll()).thenReturn(List.of(order));

        List<OrderResponseDTO> result = orderService.getAllOrders();

        assertEquals(1, result.size());
        assertEquals("Deekshitha", result.get(0).getCustomerName());
    }

    // ✅ 3. GET ORDER BY ID
    @Test
    void testGetOrderById() {

        when(orderRepository.findById(1L))
                .thenReturn(Optional.of(order));

        OrderResponseDTO result =
                orderService.getOrderById(1L);

        assertEquals("Deekshitha", result.getCustomerName());
    }

    // ✅ 4. GET ORDER BY ID (NOT FOUND)
    @Test
    void testGetOrderById_NotFound() {

        when(orderRepository.findById(1L))
                .thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            orderService.getOrderById(1L);
        });

        assertTrue(ex.getMessage().contains("Order not found"));
    }


}
