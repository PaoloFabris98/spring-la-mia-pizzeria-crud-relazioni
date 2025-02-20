package com.example.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring_la_mia_pizzeria_crud.model.Pizza;
import com.example.spring_la_mia_pizzeria_crud.repository.PizzaRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String seePizza(@RequestParam(name = "query") String query, Model model, HttpServletRequest request) {
        Pizza pizza = pizzaRepository.findByNomeContaining(query).stream().findFirst().orElse(null);

        if (pizza == null) {
            model.addAttribute("message", "Pizza non trovata");
        } else {
            model.addAttribute("currentURI", request.getRequestURI());
            model.addAttribute("pizzas", pizza);
        }

        return "pizza";
    }

    @GetMapping("/search-pizza")
    public String searchPizza(@RequestParam(name = "pizzas") String pizzas, Model model, HttpServletRequest request) {
        List<Pizza> pizze = pizzaRepository.findByNomeContaining(pizzas);
        if (pizzas == "" || pizzas == null) {
            return "redirect:/";
        } else {

            model.addAttribute("currentURI", request.getRequestURI());
            model.addAttribute("pizzas", pizze);
            return "pizza";
        }
    }

    @GetMapping("/crea_pizza")
    public String addPizza(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "addPizza";
    }

    @PostMapping("/crea_pizza")
    public String addPizza(@Valid @ModelAttribute("pizza") Pizza formpizza, BindingResult bindingResult, Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "addPizza";
        }

        pizzaRepository.save(formpizza);

        redirectAttributes.addFlashAttribute("message", "La tua pizza è stata creata");
        redirectAttributes.addFlashAttribute("messageClass", "alert-success");

        return "redirect:/";
    }

    @GetMapping("/contatti")
    public String contatti(Model model) {
        return "contatti";
    }
}
