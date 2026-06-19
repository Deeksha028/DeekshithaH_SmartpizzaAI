package com.wipro.smartpizza.service;
 
import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.smartpizza.entity.Pizza;
import com.wipro.smartpizza.repository.PizzaRepository;

import lombok.RequiredArgsConstructor;
 
@Service
@RequiredArgsConstructor
public class PizzaServiceImpl implements PizzaService {
 
    private final PizzaRepository pizzaRepository;
 
    @Override
    public Pizza addPizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }
 
    @Override
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }
 
    @Override
    public Pizza getPizzaById(Long id) {
        return pizzaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pizza not found"));
    }
 
    @Override
    public Pizza updatePizza(Long id, Pizza pizza) {
 
        Pizza existing = getPizzaById(id);
 
        existing.setName(pizza.getName());
        existing.setDescription(pizza.getDescription());
        existing.setPrice(pizza.getPrice());
        existing.setSize(pizza.getSize());
 
        return pizzaRepository.save(existing);
    }
 
    @Override
    public void deletePizza(Long id) {
        pizzaRepository.deleteById(id);
    }
}