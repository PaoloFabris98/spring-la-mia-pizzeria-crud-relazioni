package com.example.spring_la_mia_pizzeria_crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Il nome non può essere vuoto")
    private String nome;

    @Lob
    @NotBlank(message = "La descrizione non può essere vuota")
    private String descrizione;

    @NotBlank(message = "Non può non esserci un'immagine")
    private String foto;

    @NotNull(message = "Il prezzo non può essere nullo")
    private double prezzo;

    public Pizza() {
    }

    public Pizza(int id, String nome, String descrizione, String foto, double prezzo) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.foto = foto;
        this.prezzo = prezzo;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getFoto() {
        return this.foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public double getPrezzo() {
        return this.prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

}
