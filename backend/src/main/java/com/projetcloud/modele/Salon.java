package com.projetcloud.modele;

import java.util.ArrayList;
import java.util.UUID;

public class Salon {
    private String id;
    private ArrayList<String> listeJoueur;

    public Salon(String id) {
        this.listeJoueur = new ArrayList<>();
        this.id = id;
    }
    public Salon(){
        this.listeJoueur = new ArrayList<>();
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
