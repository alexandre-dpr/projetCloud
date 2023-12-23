package com.projetcloud.service;

import com.projetcloud.dto.response.CoupDTO;
import com.projetcloud.dto.response.NomJoueur;
import com.projetcloud.exceptions.*;
import com.projetcloud.modele.Puissance4;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

/**
 * Facade de l'application
 */
public interface IFacade {
    /**
     * Jouer un coup
     * @param idPartie : id de la partie

     */

    Puissance4 jouerCoup(UUID idPartie, CoupDTO coupDTO) throws MauvaisesCoordonneesExcpetion, PartieInexistanceException, CoupNonAutoriseException, MauvaisTourException, PartieTermineException;

    Puissance4 creerPartie(UUID id, ArrayList<String> listeJoueur);

    /**
     * Retourne l'état de la partie actuelle.
     *
     * @param idPartie : id de la partie
     * @return : JSON de la partie
     */
     Puissance4 getPartie(UUID idPartie) throws PartieInexistanceException;


    UUID creerSalon(String pseudo);


    UUID rejoindreSalon(UUID idSalon, NomJoueur pseudoJoueur) throws TropDeJoueurException;

    boolean getSalon(UUID idSalon) throws SalonInexistantException;

    Map<UUID, ArrayList<String>> getSalons() throws SalonInexistantException;
}
