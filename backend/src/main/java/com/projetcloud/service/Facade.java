package com.projetcloud.service;


import com.projetcloud.exceptions.MauvaisesCoordonneesExcpetion;
import org.springframework.stereotype.Component;

/**
 * {@inheritDoc}
 */
@Component
public class Facade implements IFacade{

    /**
     * {@inheritDoc}
     */
    @Override
    public void jouerCoup(String idPartie,int x, int y) throws MauvaisesCoordonneesExcpetion {
        /*
        TODO :
         - Aller chercher la partie en base, via le numPartie. Si non existant : throw PartieInexistanteException
         - Vérifs : coordonnées dans la grille + pas de pion déjà placé.
         - Modifier l'état de la partie
         */
        if (x<0 || y<0){
            throw new MauvaisesCoordonneesExcpetion();
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getPartie(String idPartie){
        /*
        TODO :
         - Aller chercher la partie en base, via le numPartie. Si non existant : throw PartieInexistanteException
         - Retourner un JSON de la partie
         */
        return null;
    }


}
