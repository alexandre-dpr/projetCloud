package com.projetcloud.service;

import com.projetcloud.dto.request.SalonDTO;
import com.projetcloud.dto.response.CoupDTO;
import com.projetcloud.dto.response.NomJoueur;
import com.projetcloud.exceptions.*;
import com.projetcloud.modele.Puissance4;

import java.util.ArrayList;

/**
 * Facade de l'application
 */
public interface IFacade {
    /**
     * Jouer un coup
     * @param idPartie : id de la partie

     */



    Puissance4 jouerCoup(String idPartie, CoupDTO coupDTO) throws MauvaisesCoordonneesExcpetion, PartieInexistanceException, CoupNonAutoriseException, MauvaisTourException, PartieTermineException, JoueurInexistantException;

    Puissance4 getPartie(String idPartie) throws PartieInexistanceException;

    String creerSalon(String pseudo) throws JoueurInexistantException;

    String rejoindreSalon(String idSalon, NomJoueur pseudoJoueur) throws TropDeJoueurException, SalonInexistantException, JoueurInexistantException, DejaDansSalonException;

    boolean getSalon(String idSalon) throws SalonInexistantException, PartieAlreadyUsedException;

    ArrayList<SalonDTO> getSalons() throws SalonInexistantException;
}
