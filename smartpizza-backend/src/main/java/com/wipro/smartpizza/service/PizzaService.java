package com.wipro.smartpizza.service;
 
import java.util.List;

import com.wipro.smartpizza.entity.Pizza;
 
public interface PizzaService {
 
    Pizza addPizza(Pizza pizza);
 
    List<Pizza> getAllPizzas();
 
    Pizza getPizzaById(Long id);
 
    Pizza updatePizza(Long id, Pizza pizza);
 
    void deletePizza(Long id);
}