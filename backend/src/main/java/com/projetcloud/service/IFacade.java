package com.projetcloud.service;

import com.projetcloud.exceptions.*;
import com.projetcloud.modele.Puissance4;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Facade de l'application
 */
public interface IFacade {
    /**
     * Jouer un coup
     * @param idPartie : id de la partie

     */

    Puissance4 jouerCoup(UUID idPartie, int colonne, String joueur) throws MauvaisesCoordonneesExcpetion, PartieInexistanceException, CoupNonAutoriseException, MauvaisTourException, PartieTermineException;

    Puissance4 creerPartie(UUID id, ArrayList<String> listeJoueur);

    /**
     * Retourne l'état de la partie actuelle.
     *
     * @param idPartie : id de la partie
     * @return : JSON de la partie
     */
     Puissance4 getPartie(UUID idPartie) throws PartieInexistanceException;


    UUID creerSalon(String pseudo);

    UUID rejoindreSalon(UUID idSalon, String pseudoJoueur) throws TropDeJoueurException;

    ArrayList<String> getSalon(UUID idSalon) throws SalonInexistantException;
}
