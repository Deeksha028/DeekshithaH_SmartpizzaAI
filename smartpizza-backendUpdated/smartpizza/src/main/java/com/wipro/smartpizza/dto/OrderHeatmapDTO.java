package com.wipro.smartpizza.dto;
 
import lombok.AllArgsConstructor;
import lombok.Data;
 
@Data
@AllArgsConstructor
public class OrderHeatmapDTO {
 
    private String location;
    private Long orders;
}