package com.wipro.smartpizza.service;
 
import java.util.ArrayList;
import java.util.List;
 
import org.springframework.stereotype.Service;
 
import com.wipro.smartpizza.dto.RecommendationResponseDTO;
 
@Service
public class RecommendationServiceImpl implements RecommendationService {
 
    @Override
    public List<RecommendationResponseDTO> getRecommendations(Long userId) {
 
        List<RecommendationResponseDTO> list = new ArrayList<>();
 
        list.add(
            RecommendationResponseDTO.builder()
                .pizza("Veg Pizza")
                .combo("Veg Pizza + Coke")
                .reason("Based on your recent orders")
                .build()
        );
 
        list.add(
            RecommendationResponseDTO.builder()
                .pizza("Farmhouse Pizza")
                .combo("Farmhouse Pizza + Garlic Bread")
                .reason("Popular combo this week")
                .build()
        );
 
        list.add(
            RecommendationResponseDTO.builder()
                .pizza("Margherita")
                .combo("Margherita + Pepsi")
                .reason("AI Recommended")
                .build()
        );
 
        return list;
    }
}