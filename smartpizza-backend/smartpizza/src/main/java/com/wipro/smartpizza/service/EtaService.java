package com.wipro.smartpizza.service;
 
import com.wipro.smartpizza.dto.EtaResponseDTO;
 
public interface EtaService {
 
    EtaResponseDTO getEta(Long orderId);
}