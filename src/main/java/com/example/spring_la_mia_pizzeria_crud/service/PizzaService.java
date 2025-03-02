package com.example.spring_la_mia_pizzeria_crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring_la_mia_pizzeria_crud.model.Ingrediente;
import com.example.spring_la_mia_pizzeria_crud.model.Pizza;
import com.example.spring_la_mia_pizzeria_crud.repository.IngredientiRepository;
import com.example.spring_la_mia_pizzeria_crud.repository.PizzaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private IngredientiRepository ingredienteRepository;

    @Transactional
    public void aggiungiPizza(Pizza pizza, List<String> ingredientiNomi) {
        List<Ingrediente> ingredienti = ingredienteRepository.findByNomeIn(ingredientiNomi);

        if (ingredienti.size() != ingredientiNomi.size()) {
            throw new IllegalArgumentException("Uno o più ingredienti non esistono nel database.");
        }

        pizza.setIngredienti(ingredienti);
        pizzaRepository.save(pizza);
    }

    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    public Optional<Pizza> findById(int Id) {
        return pizzaRepository.findById(Id);
    }

    public List<Pizza> findByNomeContaining(String nome) {
        return pizzaRepository.findByNomeContaining(nome);
    }

    public Pizza save(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public void deleteById(int id) {
        pizzaRepository.deleteById(id);
    }
}
