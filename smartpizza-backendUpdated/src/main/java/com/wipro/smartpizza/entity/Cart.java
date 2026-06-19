package com.wipro.smartpizza.entity;
 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String userEmail;
 
    private Long pizzaId;
 
    private String pizzaName;
 
    private Integer quantity;
 
    private Double price;
 
    private Double totalPrice;
 
    @ManyToOne
    @JoinColumn(name = "pizzaId", referencedColumnName = "id",
            insertable = false, updatable = false)
    private Pizza pizza;
}