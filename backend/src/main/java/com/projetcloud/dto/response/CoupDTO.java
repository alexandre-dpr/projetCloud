package com.projetcloud.dto.response;

public class CoupDTO{
    private int colonne;
    private String joueur;

    public CoupDTO(int colonne, String joueur) {
        this.colonne = colonne;
        this.joueur = joueur;
    }
    public CoupDTO(){

    }

    public int getColonne() {
        return colonne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public String getJoueur() {
        return joueur;
    }

    public void setJoueur(String joueur) {
        this.joueur = joueur;
    }
}
