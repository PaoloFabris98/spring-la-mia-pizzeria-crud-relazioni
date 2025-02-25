package com.example.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import com.example.spring_la_mia_pizzeria_crud.model.Pizza;
import com.example.spring_la_mia_pizzeria_crud.repository.PizzaRepository;

@Controller
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

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

    @GetMapping("/searchPizza")
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

    @GetMapping("/creaPizza")
    public String addPizza(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "addPizza";
    }

    @PostMapping("/creaPizza")
    public String addPizza(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "addPizza";
        }

        pizzaRepository.save(formPizza);

        redirectAttributes.addFlashAttribute("message", "La tua pizza è stata creata");
        redirectAttributes.addFlashAttribute("messageClass", "alert-success");

        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editPizza(@PathVariable Integer id, Model model) {
        model.addAttribute("pizza", pizzaRepository.findById(id).get());
        return "editPizza";
    }

    @PostMapping("/edit/{id}")
    public String editPizza(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "editPizza";
        }

        pizzaRepository.save(formPizza);

        redirectAttributes.addFlashAttribute("message", "Pizza modificata con successo");
        redirectAttributes.addFlashAttribute("messageClass", "alert-success");

        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deletePizza(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Pizza pizza = pizzaRepository.findById(id).get();
        pizzaRepository.delete(pizza);
        redirectAttributes.addFlashAttribute("message",
                "La pizza: " + pizza.getNome() + ", è stata cancellata con successo.");
        redirectAttributes.addFlashAttribute("messageClass", "alert-danger");

        return "redirect:/";
    }
}