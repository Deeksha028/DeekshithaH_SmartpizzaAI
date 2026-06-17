package com.wipro.smartpizza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.smartpizza.dto.InvoiceDTO;
import com.wipro.smartpizza.dto.PaymentRequestDTO;
import com.wipro.smartpizza.dto.PaymentResponseDTO;
import com.wipro.smartpizza.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin("*")
public class PaymentController {

    @Autowired
    private PaymentService service;

    // ✅ Save payment
    @PostMapping
    public PaymentResponseDTO makePayment(
            @RequestBody PaymentRequestDTO dto) {
    	 System.out.println("========== PAYMENT HIT ==========");
         System.out.println(dto.getOrderId());

        return service.makePayment(dto);
    }

    
    // ✅ Get all payments
    @GetMapping
    public List<PaymentResponseDTO> getAllPayments() {

        return service.getAllPayments();
    }

    // ✅ Get payment by ID
    @GetMapping("/{id}")
    public PaymentResponseDTO getPaymentById(
            @PathVariable Long id) {

        return service.getPaymentById(id);
    }

    // ✅ Get invoice
    @GetMapping("/invoice/{paymentId}")
    public InvoiceDTO getInvoice(
            @PathVariable Long paymentId) {

        return service.getInvoice(paymentId);
    }
}