package com.example.spring_la_mia_pizzeria_crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_la_mia_pizzeria_crud.model.Ingrediente;

@Repository
public interface IngredientiRepository extends JpaRepository<Ingrediente, Integer> {
    List<Ingrediente> findByNomeIn(List<String> Nomi);
}
