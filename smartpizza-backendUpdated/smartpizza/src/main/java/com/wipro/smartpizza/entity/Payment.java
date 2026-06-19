package com.wipro.smartpizza.entity;
 
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    //private Long orderId;
 
    private Double amount;
 
    private Double tax;
 
    private Double finalAmount;
 
    private String paymentMethod;
 
    private String paymentGateway;
 
    private String paymentStatus;
 
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private PizzaOrder order;
}