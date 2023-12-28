package com.projetcloud.modele;

import com.projetcloud.util.User;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.UUID;

public class Salon {
    @Schema(description = "Id", example = "1")
    private String id;
    @Schema(description = "User")
    private ArrayList<User> listeJoueur;

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

    public ArrayList<User> getListeJoueur() {
        return listeJoueur;
    }

    public void setListeJoueur(ArrayList<User> listeJoueur) {
        this.listeJoueur = listeJoueur;
    }
}
