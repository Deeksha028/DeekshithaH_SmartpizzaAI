package com.wipro.smartpizza.service;
 
import java.util.List;

import com.wipro.smartpizza.dto.RecommendationResponseDTO;
 
public interface RecommendationService {
 
    List<RecommendationResponseDTO> getRecommendations(Long userId);
}