package com.projetcloud.modele;

import com.projetcloud.exceptions.CoupNonAutoriseException;
import com.projetcloud.exceptions.MauvaisTourException;
import com.projetcloud.exceptions.PartieTermineException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Puissance4 implements Serializable {

    @Id
    private String id;
    private  int HAUTEUR_GRILLE = 6;
    private  int LARGEUR_GRILLE = 7;

    private ArrayList<ArrayList<CouleurPion>> matrice;
    private ArrayList<String> joueurs = new ArrayList<>();
    private int numTour;
    private boolean isPartieTerminee;
    private String winner;

    public Puissance4(ArrayList<String> joueurs) {
        this.joueurs = joueurs;
        this.numTour = 1;
        this.isPartieTerminee = false;
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
    private void initGrille2() {
        ArrayList<ArrayList<CouleurPion>> matrice = new ArrayList<>();

        for (int i = 0; i < HAUTEUR_GRILLE; i++) {
            ArrayList<CouleurPion> ligne = new ArrayList<>();
            matrice.add(ligne);
        }

        this.matrice = matrice;
    }

    /**
     * Méthode pour jouer un coup
     * @param joueur Nom du joueur pour qui l'on va jouer
     * @param colonne Colonne dans laquelle le joueur compte jouer
     * @throws MauvaisTourException Ce n'est pas le tour du joueur
     * @throws CoupNonAutoriseException Le coup n'est pas autorisé
     * @throws PartieTermineException La partie est terminée.
     */
    public void jouerTour(String joueur, int colonne) throws MauvaisTourException, CoupNonAutoriseException, PartieTermineException {
        if (!this.isPartieTerminee) {
            if (joueur.equals(getJoueurActuel())) {
                if (colonne >= 0 && colonne < LARGEUR_GRILLE && getTopLigne(colonne) != -1) {

                    int numLigne = getTopLigne(colonne);
                    ArrayList<CouleurPion> ligne = matrice.get(numLigne);
                    ligne.set(colonne, getCouleurCourante());
                    matrice.set(numLigne, ligne);

                    checkGameWin();
                    if (!this.isPartieTerminee) {
                        numTour++;
                    }

                } else {
                    throw new CoupNonAutoriseException("Ce coup n'est pas valide.");
                }
            } else {
                throw new MauvaisTourException("Ce n'est pas votre tour.");
            }
        } else {
            throw new PartieTermineException("La partie est terminée.");
        }
    }

    /**
     * Retourne le numéro de la ligne à laquelle le pion va tomber si on l'insère
     *
     * @param colonne indice colonne d'insert
     * @return ligne la plus basse
     */
    private int getTopLigne(int colonne) {
        for (int i = HAUTEUR_GRILLE - 1; i >= 0; i--) {
            if (Objects.isNull(matrice.get(i).get(colonne))) {
                return i;
            }
        }
        return -1;
    }

    private CouleurPion getCouleurCourante() {
        return numTour % 2 == 0 ? CouleurPion.JAUNE : CouleurPion.ROUGE;
    }

    private String getJoueurActuel() {
        return joueurs.get((numTour + 1) % 2);
    }

    /**
     * Parcours la grille horizontalement, verticalement puis en diagonale pour voir si un joueur a gagné
     */
    private void checkGameWin() {
        matrice.forEach(ligne -> {
            CouleurPion couleurPion = isLigneGagnante(ligne);
            handleWin(couleurPion);
        });

        if (!this.isPartieTerminee) {
            for (int i = 0; i < LARGEUR_GRILLE; i++) {
                CouleurPion couleurPion = isColonneGagnante(i, matrice);
                handleWin(couleurPion);
            }
        }

        if (!this.isPartieTerminee) {
            for (int i = 0; i < HAUTEUR_GRILLE; i++) {
                for (int j = 0; j < LARGEUR_GRILLE; j++) {
                    CouleurPion couleurPion = isDiagonaleGagnante(i, j, matrice);
                    handleWin(couleurPion);
                }
            }
        }
    }

    /**
     * Gère l'état de la partie dans le cas d'une victoire
     * @param couleurPion couleur du pion aillant gagné
     */
    private void handleWin(CouleurPion couleurPion) {
        if (!this.isPartieTerminee && Objects.nonNull(couleurPion)) {
            this.isPartieTerminee = true;
            this.winner = couleurPion == CouleurPion.ROUGE ? joueurs.get(0) : joueurs.get(1);
        }
    }

    /**
     * Retourne une couleur s'il y a un gagnant dans la liste, sinon retourne null
     *
     * @param ligne Ligne à vérifier
     * @return Couleur du gagnant ou null
     */
    private CouleurPion isLigneGagnante(ArrayList<CouleurPion> ligne) {
        int pionsAlignes = 0;
        CouleurPion lastColor = null;

        for (CouleurPion pion : ligne) {
            if (pion != null && lastColor == pion) {
                pionsAlignes++;

                if (pionsAlignes == 4) {
                    return lastColor;
                }

            } else {
                pionsAlignes = 1;
                lastColor = pion;
            }
        }
        return null;
    }

    /**
     * Retourne une couleur s'il y a un gagnant dans la colonne, sinon retourne null
     *
     * @param numColonne numColonne
     * @param matrice    matrice de partie
     * @return couleur gagnant ou null
     */
    private CouleurPion isColonneGagnante(int numColonne, ArrayList<ArrayList<CouleurPion>> matrice) {
        int pionsAlignes = 0;
        CouleurPion lastColor = null;

        for (int i = 0; i < HAUTEUR_GRILLE; i++) {
            CouleurPion pion = matrice.get(i).get(numColonne);
            if (pion != null && lastColor == pion) {
                pionsAlignes++;

                if (pionsAlignes == 4) {
                    return lastColor;
                }

            } else {
                pionsAlignes = 1;
                lastColor = pion;
            }
        }
        return null;
    }

    /**
     * Retourne une couleur s'il y a un gagnant dans la diagonale, sinon retourne null
     *
     * @param numLigne   Ligne
     * @param numColonne Col
     * @param matrice    matrice partie
     * @return Couleur gagnant ou null
     */
    private CouleurPion isDiagonaleGagnante(int numLigne, int numColonne, ArrayList<ArrayList<CouleurPion>> matrice) {
        int initialLigne = numLigne;
        int initialColonne = numColonne;

        int pionsAlignes = 0;
        CouleurPion lastColor = null;

        while (numLigne >= 0 && numLigne < HAUTEUR_GRILLE && numColonne >= 0 && numColonne < LARGEUR_GRILLE) {
            CouleurPion pion = matrice.get(numLigne).get(numColonne);

            if (pion != null) {
                if (lastColor == pion) {
                    pionsAlignes++;

                    if (pionsAlignes == 4) {
                        return lastColor;
                    }

                } else {
                    pionsAlignes = 1;
                    lastColor = pion;
                }
            }

            numLigne--;
            numColonne++;
        }

        pionsAlignes = 0;
        lastColor = null;
        numLigne = initialLigne;
        numColonne = initialColonne;

        while (numLigne >= 0 && numLigne < HAUTEUR_GRILLE && numColonne >= 0 && numColonne < LARGEUR_GRILLE) {
            CouleurPion pion = matrice.get(numLigne).get(numColonne);

            if (pion != null) {
                if (lastColor == pion) {
                    pionsAlignes++;

                    if (pionsAlignes == 4) {
                        return lastColor;
                    }

                } else {
                    pionsAlignes = 1;
                    lastColor = pion;
                }
            }

            numLigne--;
            numColonne--;
        }
        return null;
    }



    /**
     * Méthode pour debug
     * @hidden
     */
    private void printGame() {
        System.out.println("\n==== TOUR " + getNumTour() + " ====\n");
        matrice.forEach(System.out::println);
        System.out.println("\n================");
    }
}
