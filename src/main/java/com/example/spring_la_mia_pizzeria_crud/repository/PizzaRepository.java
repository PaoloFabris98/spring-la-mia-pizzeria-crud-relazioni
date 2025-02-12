package com.example.spring_la_mia_pizzeria_crud.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.spring_la_mia_pizzeria_crud.java.database_controller.*;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    ArrayList<Pizza> findById(int id);

    /*
     * @Query("SELECT p FROM Pizza p WHERE p.id = :id")
     * Pizza findPizzaById(@Param("id") int id);
     */
}
