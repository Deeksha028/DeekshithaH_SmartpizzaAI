package com.wipro.smartpizza.entity;
 
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
 
@Entity
@Data
public class Pizza {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String name;
 
    private String description;
 
    private Double price;
 
    private String size;
 
    @OneToMany(mappedBy = "pizza")
    @JsonIgnore
    private List<Cart> carts;
}