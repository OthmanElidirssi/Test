package com.example.plantest;

import java.time.LocalDate;

public class Person {

    private int id;
    private String nom;
    private String prenom;
    private String date;

    public Person(int id, String nom, String prenom, String date) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
