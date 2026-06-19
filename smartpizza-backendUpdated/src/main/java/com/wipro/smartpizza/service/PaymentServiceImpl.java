package com.wipro.smartpizza.service;
 
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.smartpizza.dto.DeliveryDTO;
import com.wipro.smartpizza.dto.InvoiceDTO;
import com.wipro.smartpizza.dto.PaymentRequestDTO;
import com.wipro.smartpizza.dto.PaymentResponseDTO;
import com.wipro.smartpizza.entity.Payment;
import com.wipro.smartpizza.entity.PizzaOrder;
import com.wipro.smartpizza.repository.OrderRepository;
import com.wipro.smartpizza.repository.PaymentRepository;
 
@Service
public class PaymentServiceImpl implements PaymentService {
 
    @Autowired
    private PaymentRepository repository;
    
    @Autowired
    private DeliveryService deliveryService;
    
    @Autowired
    private OrderRepository orderRepository;
 
    @Override
    public PaymentResponseDTO makePayment(PaymentRequestDTO dto) {
    	
    	 Double tax = dto.getAmount() * 0.18;
     	Double finalAmount = dto.getAmount() + tax;
    	
    	PizzaOrder order = orderRepository.findById(dto.getOrderId())
    	        .orElseThrow(() -> new RuntimeException("Order not found"));
    	 
    	Payment payment = Payment.builder()
    	        .order(order)
    	        .amount(dto.getAmount())
    	        .tax(tax)
    	        .finalAmount(finalAmount)
    	        .paymentMethod(dto.getPaymentMethod())
    	        .paymentGateway(dto.getPaymentGateway())
    	        .paymentStatus("SUCCESS")
    	        .build();
        
       
 
        Payment saved = repository.save(payment);
        
        DeliveryDTO deliveryDTO = DeliveryDTO.builder()
                .orderId(order.getId())
                .deliveryPerson("Ravi")
                .currentLocation("Pizza Store")
                .etaMinutes(30)
                .status("RECEIVED")
                .build();
         
        deliveryService.createDelivery(deliveryDTO);
 
        return mapToDTO(saved);
    }
 
    @Override
    public List<PaymentResponseDTO> getAllPayments() {
 
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
 
    @Override
    public PaymentResponseDTO getPaymentById(Long id) {
 
        Payment payment = repository.findById(id)
                .orElseThrow();
 
        return mapToDTO(payment);
    }
 
    private PaymentResponseDTO mapToDTO(Payment payment) {
 
    	return PaymentResponseDTO.builder()
    	        .id(payment.getId())
    	        .orderId(payment.getOrder().getId())
    	        .amount(payment.getAmount())
    	        .tax(payment.getTax())
    	        .finalAmount(payment.getFinalAmount())
    	        .paymentMethod(payment.getPaymentMethod())
    	        .paymentGateway(payment.getPaymentGateway())
    	        .paymentStatus(payment.getPaymentStatus())
    	        .build();
    }
    @Override
    public InvoiceDTO getInvoice(Long paymentId) {
     
        Payment payment = repository.findById(paymentId)
                .orElseThrow(() ->
                        new RuntimeException("Payment not found"));
     
        return InvoiceDTO.builder()
                .paymentId(payment.getId())
                .orderId(payment.getOrder().getId())
                .amount(payment.getAmount())
                .tax(payment.getTax())
                .finalAmount(payment.getFinalAmount())
                .paymentMethod(payment.getPaymentMethod())
                .paymentStatus(payment.getPaymentStatus())
                .build();
    }
}