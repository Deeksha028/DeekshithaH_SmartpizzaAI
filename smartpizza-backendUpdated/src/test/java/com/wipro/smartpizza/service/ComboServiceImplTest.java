package com.wipro.smartpizza.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.wipro.smartpizza.dto.ComboResponseDTO;

import org.junit.jupiter.api.Test;

public class ComboServiceImplTest {

    private final ComboServiceImpl comboService = new ComboServiceImpl();

    @Test
    void testGetCombos() {

        List<ComboResponseDTO> combos = comboService.getCombos();

        // ✅ check not null
        assertNotNull(combos);

        // ✅ check size
        assertEquals(3, combos.size());

        // ✅ first combo
        ComboResponseDTO combo1 = combos.get(0);
        assertEquals("Margherita + Coke", combo1.getComboName());
        assertEquals(299.0, combo1.getComboPrice());
        assertEquals("Best seller combo", combo1.getDescription());

        // ✅ second combo
        ComboResponseDTO combo2 = combos.get(1);
        assertEquals("Farmhouse + Garlic Bread", combo2.getComboName());
        assertEquals(499.0, combo2.getComboPrice());

        // ✅ third combo
        ComboResponseDTO combo3 = combos.get(2);
        assertEquals("Veg Loaded + Pepsi", combo3.getComboName());
        assertEquals(399.0, combo3.getComboPrice());
    }
}