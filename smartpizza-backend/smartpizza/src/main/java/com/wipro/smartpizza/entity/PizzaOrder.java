package com.wipro.smartpizza.entity;
 
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PizzaOrder {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
 
    private String customerName;
 
    private String customerEmail;
 
    private Double totalAmount;
 
    private String status;
 
    private String paymentStatus;
 
    private String deliveryStatus;
 
    private Integer etaMinutes;
 
    private String deliveryPerson;
 
    private LocalDateTime createdAt;
    
    @OneToOne(mappedBy = "order",fetch = FetchType.LAZY)
    private Payment payment;
    
 
    private LocalDateTime updatedAt;
 
    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;
 
    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }
 
    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}