package com.wipro.smartpizza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.smartpizza.dto.ComboResponseDTO;
import com.wipro.smartpizza.service.ComboService;

@RestController
@RequestMapping("/api/combos")
public class ComboController {
 
    @Autowired
    private ComboService service;
 
    @GetMapping
    public List<ComboResponseDTO> getCombos() {
        return service.getCombos();
    }
}
