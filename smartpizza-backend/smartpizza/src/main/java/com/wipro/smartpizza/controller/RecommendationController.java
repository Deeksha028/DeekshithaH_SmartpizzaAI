package com.wipro.smartpizza.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
 
import com.wipro.smartpizza.dto.RecommendationResponseDTO;
import com.wipro.smartpizza.service.RecommendationService;
 
@RestController
@RequestMapping("/api/recommendations")
@CrossOrigin(origins = "http://localhost:3000")
public class RecommendationController {
 
    @Autowired
    private RecommendationService recommendationService;
 
    @GetMapping("/{userId}")
    public List<RecommendationResponseDTO> getRecommendations(
            @PathVariable Long userId) {
 
        return recommendationService.getRecommendations(userId);
    }
}