package com.wipro.smartpizza.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wipro.smartpizza.dto.DeliveryDTO;
import com.wipro.smartpizza.dto.RouteResponseDTO;
import com.wipro.smartpizza.entity.Delivery;
import com.wipro.smartpizza.entity.PizzaOrder;
import com.wipro.smartpizza.repository.DeliveryRepository;
import com.wipro.smartpizza.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
public class DeliveryServiceImplTest {

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private DeliveryServiceImpl deliveryService;

    private PizzaOrder order;
    private Delivery delivery;
    private DeliveryDTO dto;

    @BeforeEach
    void setup() {

        // ✅ Order setup
        order = new PizzaOrder();
        order.setId(1L);

        // ✅ Delivery DTO
        dto = DeliveryDTO.builder()
                .id(1L)
                .orderId(1L)
                .deliveryPerson("John")
                .currentLocation("Bangalore")
                .etaMinutes(25)
                .status("PENDING")
                .build();

        // ✅ Delivery Entity
        delivery = Delivery.builder()
                .id(1L)
                .order(order)
                .deliveryPerson("John")
                .currentLocation("Bangalore")
                .etaMinutes(25)
                .status("PENDING")
                .build();
    }

    // ✅ 1. CREATE DELIVERY
    @Test
    void testCreateDelivery() {

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(deliveryRepository.save(any(Delivery.class))).thenReturn(delivery);

        DeliveryDTO result = deliveryService.createDelivery(dto);

        assertNotNull(result);
        assertEquals("John", result.getDeliveryPerson());
        assertEquals("Bangalore", result.getCurrentLocation());
        assertEquals(25, result.getEtaMinutes());
    }

    // ✅ 2. CREATE DELIVERY (ORDER NOT FOUND)
    @Test
    void testCreateDelivery_OrderNotFound() {

        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            deliveryService.createDelivery(dto);
        });
    }

    // ✅ 3. GET DELIVERY BY ID
    @Test
    void testGetDeliveryById() {

        when(deliveryRepository.findById(1L)).thenReturn(Optional.of(delivery));

        DeliveryDTO result = deliveryService.getDeliveryById(1L);

        assertEquals("John", result.getDeliveryPerson());
    }

    // ✅ 4. GET DELIVERY BY ORDER ID
    @Test
    void testGetDeliveryByOrderId() {

        when(deliveryRepository.findByOrder_Id(1L))
                .thenReturn(Optional.of(delivery));

        DeliveryDTO result = deliveryService.getDeliveryByOrderId(1L);

        assertEquals("John", result.getDeliveryPerson());
    }

    // ✅ 5. GET ALL DELIVERIES
    @Test
    void testGetAllDeliveries() {

        when(deliveryRepository.findAll()).thenReturn(List.of(delivery));

        List<DeliveryDTO> result = deliveryService.getAllDeliveries();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getDeliveryPerson());
    }

    // ✅ 6. UPDATE DELIVERY STATUS
    @Test
    void testUpdateDeliveryStatus() {

        when(deliveryRepository.findById(1L)).thenReturn(Optional.of(delivery));
        when(deliveryRepository.save(any(Delivery.class))).thenReturn(delivery);

        DeliveryDTO result =
                deliveryService.updateDeliveryStatus(1L, "DELIVERED");

        assertEquals("DELIVERED", result.getStatus());
    }
}