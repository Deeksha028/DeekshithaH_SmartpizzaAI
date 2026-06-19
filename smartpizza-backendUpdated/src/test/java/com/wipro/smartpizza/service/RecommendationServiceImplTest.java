package com.wipro.smartpizza.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.wipro.smartpizza.dto.RecommendationResponseDTO;

import org.junit.jupiter.api.Test;

public class RecommendationServiceImplTest {

    private final RecommendationServiceImpl recommendationService =
            new RecommendationServiceImpl();

    // ✅ TEST getRecommendations()
    @Test
    void testGetRecommendations() {

        List<RecommendationResponseDTO> result =
                recommendationService.getRecommendations(1L);

        // ✅ check not null
        assertNotNull(result);

        // ✅ check size
        assertEquals(3, result.size());

        // ✅ check first recommendation
        RecommendationResponseDTO r1 = result.get(0);
        assertEquals("Veg Pizza", r1.getPizza());
        assertEquals("Veg Pizza + Coke", r1.getCombo());
        assertEquals("Based on your recent orders", r1.getReason());

        // ✅ check second recommendation
        RecommendationResponseDTO r2 = result.get(1);
        assertEquals("Farmhouse Pizza", r2.getPizza());
        assertEquals("Farmhouse Pizza + Garlic Bread", r2.getCombo());
        assertEquals("Popular combo this week", r2.getReason());

        // ✅ check third recommendation
        RecommendationResponseDTO r3 = result.get(2);
        assertEquals("Margherita", r3.getPizza());
        assertEquals("Margherita + Pepsi", r3.getCombo());
        assertEquals("AI Recommended", r3.getReason());
    }
}