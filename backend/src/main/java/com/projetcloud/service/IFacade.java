package com.projetcloud.service;

import com.projetcloud.exceptions.MauvaisesCoordonneesExcpetion;

/**
 * Facade de l'application
 */
public interface IFacade {
    /**
     * Jouer un coup
     * @param idPartie : id de la partie
     * @param x : coordonnée x du coup
     * @param y : coordonnée y du coup
     */
    public void jouerCoup(String idPartie,int x, int y) throws MauvaisesCoordonneesExcpetion;

    /**
     * Retourne l'état de la partie actuelle.
     * @param idPartie : id de la partie
     * @return : JSON de la partie
     */
    public String getPartie(String idPartie);


}
