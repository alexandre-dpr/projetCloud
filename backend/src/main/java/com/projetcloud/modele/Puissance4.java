package com.projetcloud.modele;

import com.projetcloud.exceptions.MauvaisTourException;

import java.util.ArrayList;

public class Puissance4 {

    private final int HAUTEUR_GRILLE = 6;
    private final int LARGEUR_GRILLE = 7;

    private ArrayList<ArrayList<CouleurPion>> matrice;
    private ArrayList<String> joueurs;
    private int numTour;

    public Puissance4(ArrayList<String> joueurs) {
        this.joueurs = joueurs;
        this.numTour = 1;
        initGrille();
    }

    private void initGrille() {
        ArrayList<ArrayList<CouleurPion>> matrice = new ArrayList<>();

        for (int i = 0; i < HAUTEUR_GRILLE; i++) {
            ArrayList<CouleurPion> ligne = new ArrayList<>();

            for (int j = 0; j < LARGEUR_GRILLE; j++) {
                ligne.add(null);
            }

            matrice.add(ligne);
        }

        this.matrice = matrice;
    }

    public void jouerTour(String joueur, int colonne) throws MauvaisTourException {
        if (joueur.equals(joueurs.get(numTour))) {

        } else {
            throw new MauvaisTourException("Ce n'est pas votre tour.");
        }
    }
}
