package com.projetcloud.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public class CoupDTO{
    @Schema(description = "Colonne", example = "1")
    private int colonne;
    @Schema(description = "Joueur", example = "joueur1")
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
