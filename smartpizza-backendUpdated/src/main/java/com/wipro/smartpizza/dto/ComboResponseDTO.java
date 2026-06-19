package com.wipro.smartpizza.dto;
 
import lombok.AllArgsConstructor;
import lombok.Data;
 
@Data
@AllArgsConstructor
public class ComboResponseDTO {
 
    private String comboName;
    private Double comboPrice;
    private String description;
}