package com.wipro.smartpizza.controller;
 
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.smartpizza.entity.Pizza;
import com.wipro.smartpizza.service.PizzaService;

import lombok.RequiredArgsConstructor;
 
@RestController
@RequestMapping("/api/pizzas")
@RequiredArgsConstructor
public class PizzaController {
 
    private final PizzaService pizzaService;
 
    @PostMapping
    public Pizza addPizza(@RequestBody Pizza pizza) {
        return pizzaService.addPizza(pizza);
    }
 
    @GetMapping
    public List<Pizza> getAllPizzas() {
        return pizzaService.getAllPizzas();
    }
 
    @GetMapping("/{id}")
    public Pizza getPizza(@PathVariable Long id) {
        return pizzaService.getPizzaById(id);
    }
 
    @PutMapping("/{id}")
    public Pizza updatePizza(@PathVariable Long id,
                             @RequestBody Pizza pizza) {
        return pizzaService.updatePizza(id, pizza);
    }
 
    @DeleteMapping("/{id}")
    public String deletePizza(@PathVariable Long id) {
 
        pizzaService.deletePizza(id);
 
        return "Pizza deleted successfully";
    }
}