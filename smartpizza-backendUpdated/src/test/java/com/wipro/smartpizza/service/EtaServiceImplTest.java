package com.wipro.smartpizza.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.wipro.smartpizza.dto.EtaResponseDTO;
import com.wipro.smartpizza.entity.Delivery;
import com.wipro.smartpizza.repository.DeliveryRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EtaServiceImplTest {

    @Mock
    private DeliveryRepository deliveryRepository;

    @InjectMocks
    private EtaServiceImpl etaService;

    // ✅ 1. GET ETA (SUCCESS)
    @Test
    void testGetEta_Success() {

        Delivery delivery = new Delivery();
        delivery.setId(1L);
        delivery.setDeliveryPerson("John");
        delivery.setEtaMinutes(25);
        delivery.setStatus("ON THE WAY");

        when(deliveryRepository.findByOrder_Id(1L))
                .thenReturn(Optional.of(delivery));

        EtaResponseDTO result = etaService.getEta(1L);

        assertNotNull(result);
        assertEquals(1L, result.getOrderId());
        assertEquals("John", result.getDeliveryPerson());
        assertEquals(25, result.getEtaMinutes());
        assertEquals("ON THE WAY", result.getStatus());
    }

    // ✅ 2. GET ETA (DELIVERY NOT FOUND)
    @Test
    void testGetEta_NotFound() {

        when(deliveryRepository.findByOrder_Id(1L))
                .thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            etaService.getEta(1L);
        });

        assertEquals("Delivery not found", ex.getMessage());
    }
}