package com.wipro.smartpizza.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import com.wipro.smartpizza.dto.InvoiceDTO;
import com.wipro.smartpizza.dto.PaymentRequestDTO;
import com.wipro.smartpizza.dto.PaymentResponseDTO;
import com.wipro.smartpizza.entity.Payment;
import com.wipro.smartpizza.entity.PizzaOrder;
import com.wipro.smartpizza.repository.OrderRepository;
import com.wipro.smartpizza.repository.PaymentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private DeliveryService deliveryService;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private PaymentRequestDTO dto;
    private PizzaOrder order;
    private Payment payment;

    @BeforeEach
    void setup() {

        // ✅ Request DTO
        dto = new PaymentRequestDTO();
        dto.setOrderId(1L);
        dto.setAmount(1000.0);
        dto.setPaymentMethod("CARD");
        dto.setPaymentGateway("RAZORPAY");

        // ✅ Order Entity
        order = new PizzaOrder();
        order.setId(1L);

        // ✅ Payment Entity
        payment = Payment.builder()
                .id(1L)
                .order(order)
                .amount(1000.0)
                .tax(180.0)
                .finalAmount(1180.0)
                .paymentMethod("CARD")
                .paymentGateway("RAZORPAY")
                .paymentStatus("SUCCESS")
                .build();
    }

    // ✅ 1. MAKE PAYMENT
    @Test
    void testMakePayment() {

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        PaymentResponseDTO result = paymentService.makePayment(dto);

        assertNotNull(result);
        assertEquals(1000.0, result.getAmount());
        assertEquals(180.0, result.getTax());
        assertEquals(1180.0, result.getFinalAmount());
        assertEquals("SUCCESS", result.getPaymentStatus());

        // ✅ verify delivery created
        verify(deliveryService, times(1)).createDelivery(any());
    }

    // ✅ 2. MAKE PAYMENT (ORDER NOT FOUND)
    @Test
    void testMakePayment_OrderNotFound() {

        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            paymentService.makePayment(dto);
        });
    }

    // ✅ 3. GET ALL PAYMENTS
    @Test
    void testGetAllPayments() {

        when(paymentRepository.findAll()).thenReturn(List.of(payment));

        List<PaymentResponseDTO> result = paymentService.getAllPayments();

        assertEquals(1, result.size());
        assertEquals(1000.0, result.get(0).getAmount());
    }

    // ✅ 4. GET PAYMENT BY ID
    @Test
    void testGetPaymentById() {

        when(paymentRepository.findById(1L))
                .thenReturn(Optional.of(payment));

        PaymentResponseDTO result =
                paymentService.getPaymentById(1L);

        assertEquals(1000.0, result.getAmount());
    }

    // ✅ 5. GET PAYMENT BY ID (NOT FOUND)
    @Test
    void testGetPaymentById_NotFound() {

        when(paymentRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            paymentService.getPaymentById(1L);
        });
    }

    // ✅ 6. GET INVOICE
    @Test
    void testGetInvoice() {

        when(paymentRepository.findById(1L))
                .thenReturn(Optional.of(payment));

        InvoiceDTO result = paymentService.getInvoice(1L);

        assertNotNull(result);
        assertEquals(1L, result.getPaymentId());
        assertEquals(1000.0, result.getAmount());
        assertEquals(180.0, result.getTax());
        assertEquals(1180.0, result.getFinalAmount());
    }

    // ✅ 7. GET INVOICE (NOT FOUND)
    @Test
    void testGetInvoice_NotFound() {

        when(paymentRepository.findById(1L))
                .thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            paymentService.getInvoice(1L);
        });

        assertEquals("Payment not found", ex.getMessage());
    }
}