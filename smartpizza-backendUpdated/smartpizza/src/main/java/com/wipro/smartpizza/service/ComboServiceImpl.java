package com.wipro.smartpizza.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.smartpizza.dto.ComboResponseDTO;

@Service
public class ComboServiceImpl implements ComboService{
	@Override
	public List<ComboResponseDTO> getCombos() {
	 
	    return List.of(
	 
	            new ComboResponseDTO(
	                    "Margherita + Coke",
	                    299.0,
	                    "Best seller combo"),
	 
	            new ComboResponseDTO(
	                    "Farmhouse + Garlic Bread",
	                    499.0,
	                    "Family combo"),
	 
	            new ComboResponseDTO(
	                    "Veg Loaded + Pepsi",
	                    399.0,
	                    "Weekend combo")
	    );
	}
}
