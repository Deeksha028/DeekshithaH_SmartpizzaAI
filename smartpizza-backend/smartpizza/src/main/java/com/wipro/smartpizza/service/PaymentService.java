package com.wipro.smartpizza.service;
 
import java.util.List;

import com.wipro.smartpizza.dto.InvoiceDTO;
import com.wipro.smartpizza.dto.PaymentRequestDTO;
import com.wipro.smartpizza.dto.PaymentResponseDTO;
 
public interface PaymentService {
 
    PaymentResponseDTO makePayment(PaymentRequestDTO dto);
 
    List<PaymentResponseDTO> getAllPayments();
 
    PaymentResponseDTO getPaymentById(Long id);
    
    InvoiceDTO getInvoice(Long paymentId);
}