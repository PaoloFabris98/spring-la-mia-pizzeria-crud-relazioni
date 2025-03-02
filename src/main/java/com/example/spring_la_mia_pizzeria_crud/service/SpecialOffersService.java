package com.example.spring_la_mia_pizzeria_crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_la_mia_pizzeria_crud.model.SpecialOffers;
import com.example.spring_la_mia_pizzeria_crud.repository.SpecialOffersReporitory;

import jakarta.transaction.Transactional;

@Service
public class SpecialOffersService {
    @Autowired
    private SpecialOffersReporitory specialOffersReporitory;

    @Transactional
    public SpecialOffers create(SpecialOffers offerta) {
        return specialOffersReporitory.save(offerta);
    }

}
