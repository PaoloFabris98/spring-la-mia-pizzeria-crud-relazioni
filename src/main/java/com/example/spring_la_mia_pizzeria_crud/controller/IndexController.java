package com.example.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring_la_mia_pizzeria_crud.model.Pizza;
import com.example.spring_la_mia_pizzeria_crud.repository.PizzaRepository;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private final PizzaRepository pizzaRepository;

    public IndexController(PizzaRepository pizzaService) {
        this.pizzaRepository = pizzaService;
    }

    @GetMapping("/")
    public String getMethodName(Model model) {
        if (pizzaRepository.findAll().size() == 0) {
            model.addAttribute("pizzas", pizzaRepository.findAll());
            model.addAttribute("isValid", "false");
        } else {
            model.addAttribute("pizzas", pizzaRepository.findAll());
            model.addAttribute("isValid", "true");
        }
        ;
        return "index";
    }

    @GetMapping("/pizza")
    public String seePizza(@RequestParam(name = "query") String query, Model model) {
        Pizza pizza = pizzaRepository.findByNomeContaining(query).stream().findFirst().orElse(null);

        if (pizza == null) {
            model.addAttribute("message", "Pizza non trovata");
        } else {
            model.addAttribute("pizzas", pizza);
        }

        return "pizza";
    }

    @GetMapping("/search-pizza")
    public String searchPizza(@RequestParam(name = "pizzas") String pizzas, Model model) {
        List<Pizza> pizze = pizzaRepository.findByNomeContaining(pizzas);
        if (pizzas == "" || pizzas == null) {
            return "redirect:/";
        } else {
            model.addAttribute("pizzas", pizze);
            return "pizza";
        }
    }

    @GetMapping("/contatti")
    public String contatti(Model model) {
        return "contatti";
    }
}
