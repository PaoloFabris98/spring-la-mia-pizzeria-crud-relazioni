package com.example.spring_la_mia_pizzeria_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring_la_mia_pizzeria_crud.model.Pizza;
import com.example.spring_la_mia_pizzeria_crud.model.SpecialOffers;
import com.example.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import com.example.spring_la_mia_pizzeria_crud.service.SpecialOffersService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;;

@Controller
public class OffertaController {
    @Autowired
    SpecialOffersService specialOffersService;

    @PostMapping("/creaOfferta/{id}")
    public String addOfferta(@Valid @ModelAttribute("offerta") SpecialOffers formOfferta, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "offerta/offerta";
        }

        specialOffersService.create(formOfferta);

        redirectAttributes.addFlashAttribute("message", "L'ingrediente è stato creato con successo.");
        redirectAttributes.addFlashAttribute("messageClass", "alert-success");

        return "redirect:/";
    }
}
