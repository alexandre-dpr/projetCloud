package com.projetcloud.modele;

import java.util.ArrayList;
import java.util.UUID;

public class Salon {
    private String id;
    private ArrayList<String> listeJoueur;

    public Salon() {
        this.listeJoueur = new ArrayList<>();
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public ArrayList<String> getListeJoueur() {
        return listeJoueur;
    }

    public void setListeJoueur(ArrayList<String> listeJoueur) {
        this.listeJoueur = listeJoueur;
    }
}
