package com.example.spring_la_mia_pizzeria_crud.services;

import org.springframework.stereotype.Service;
import java.util.List;

import com.example.spring_la_mia_pizzeria_crud.repository.*;
import com.example.spring_la_mia_pizzeria_crud.java.database_controller.*;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }
}