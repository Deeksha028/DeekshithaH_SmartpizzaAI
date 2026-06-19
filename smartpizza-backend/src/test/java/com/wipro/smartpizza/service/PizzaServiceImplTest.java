package com.wipro.smartpizza.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wipro.smartpizza.entity.Pizza;
import com.wipro.smartpizza.repository.PizzaRepository;


@ExtendWith(MockitoExtension.class)
class PizzaServiceImplTest {
 
    @Mock
    private PizzaRepository pizzaRepository;
 
    @InjectMocks
    private PizzaServiceImpl pizzaService;
 
    @Test
    void testAddPizza() {
        Pizza pizza = new Pizza();
        pizza.setName("Margherita");
        pizza.setCategory("Veg");
        pizza.setPrice(299.0);
        pizza.setSize("Medium");
 
        when(pizzaRepository.save(any(Pizza.class))).thenReturn(pizza);
 
        Pizza saved = pizzaService.addPizza(pizza);
 
        assertEquals("Margherita", saved.getName());
        verify(pizzaRepository, times(1)).save(pizza);
    }
 
    @Test
    void testGetAllPizza() {
        List<Pizza> pizzas = Arrays.asList(new Pizza(), new Pizza());
 
        when(pizzaRepository.findAll()).thenReturn(pizzas);
 
        List<Pizza> result = pizzaService.getAllPizzas();
 
        assertEquals(2, result.size());
    }
 
    @Test
    void testGetPizzaById() {
        Pizza pizza = new Pizza();
        pizza.setId(1L);
        pizza.setName("Farmhouse");
 
        when(pizzaRepository.findById(1L)).thenReturn(Optional.of(pizza));
 
        Pizza result = pizzaService.getPizzaById(1L);
 
        assertEquals("Farmhouse", result.getName());
    }
 
    @Test
    void testDeletePizza() {
 
        doNothing().when(pizzaRepository).deleteById(1L);
 
        pizzaService.deletePizza(1L);
 
        verify(pizzaRepository, times(1)).deleteById(1L);
    }
}
