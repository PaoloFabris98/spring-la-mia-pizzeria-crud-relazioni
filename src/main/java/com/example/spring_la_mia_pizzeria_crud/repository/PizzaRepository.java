package com.example.spring_la_mia_pizzeria_crud.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_la_mia_pizzeria_crud.java.database_controller.*;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    ArrayList<Pizza> findById(int id);
}
