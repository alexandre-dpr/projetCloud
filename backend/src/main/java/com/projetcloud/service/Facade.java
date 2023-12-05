package com.projetcloud.service;


import com.projetcloud.exceptions.*;
import com.projetcloud.modele.Puissance4;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * {@inheritDoc}
 */
@Component
public class Facade implements IFacade{

    private Map<UUID,Puissance4> parties;

    private Map<UUID, ArrayList<String>> salon;

    public Facade(){
        this.parties = new HashMap<>();
        this.salon = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Puissance4 jouerCoup(UUID idPartie, int colonne, String joueur) throws MauvaisesCoordonneesExcpetion, PartieInexistanceException, CoupNonAutoriseException, MauvaisTourException, PartieTermineException {
        if (colonne<0){
            throw new MauvaisesCoordonneesExcpetion();
        }
        Puissance4 partie = this.getPartie(idPartie);
        partie.jouerTour(joueur,colonne);
        return partie;
    }

    @Override
    public Puissance4 creerPartie(UUID id, ArrayList<String> listeJoueur) {
        Puissance4 nouvellePartie = new Puissance4(listeJoueur);
        parties.put(id, nouvellePartie);
        return nouvellePartie;
    }

   @Override
    public Puissance4 getPartie(UUID idPartie) throws PartieInexistanceException{
        try {
            return this.parties.get(idPartie);
        }catch (Exception e){
            throw new PartieInexistanceException();
        }
    }

    @Override
    public UUID creerSalon(String pseudo) {
        UUID idUnique = UUID.randomUUID();
        ArrayList<String> joueurs = new ArrayList<>();
        joueurs.add(pseudo);
        salon.put(idUnique,joueurs);
        return idUnique;
    }

    @Override
    public UUID rejoindreSalon(UUID idSalon, String pseudoJoueur) throws TropDeJoueurException {
        ArrayList<String> joueurs = salon.get(idSalon);
        if (joueurs.size() < 2) {
            joueurs.add(pseudoJoueur);
        }else {
            throw new TropDeJoueurException();
        }
        return idSalon;
    }

    @Override
    public ArrayList<String> getSalon(UUID idSalon) throws SalonInexistantException{
        try {
            return this.salon.get(idSalon);
        }catch (Exception e){
            throw new SalonInexistantException();
        }
    }


}
