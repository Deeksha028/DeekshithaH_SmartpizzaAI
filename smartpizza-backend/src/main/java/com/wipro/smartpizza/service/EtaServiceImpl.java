package com.wipro.smartpizza.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.smartpizza.dto.EtaResponseDTO;
import com.wipro.smartpizza.entity.Delivery;
import com.wipro.smartpizza.repository.DeliveryRepository;
 
@Service
public class EtaServiceImpl implements EtaService {
 
    @Autowired
    private DeliveryRepository deliveryRepository;
 
    @Override
    public EtaResponseDTO getEta(Long orderId) {
 
        Delivery delivery =
                deliveryRepository.findByOrder_Id(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Delivery not found"));
 
        return EtaResponseDTO.builder()
                .orderId(orderId)
                .deliveryPerson(delivery.getDeliveryPerson())
                .etaMinutes(delivery.getEtaMinutes())
                .status(delivery.getStatus())
                .build();
    }
}