package com.projetcloud.service;


import com.projetcloud.dto.response.CoupDTO;
import com.projetcloud.dto.response.NomJoueur;
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
    public Puissance4 jouerCoup(UUID idPartie, CoupDTO coupDTO) throws MauvaisesCoordonneesExcpetion, PartieInexistanceException, CoupNonAutoriseException, MauvaisTourException, PartieTermineException {
        if (coupDTO.getColonne()<0){
            throw new MauvaisesCoordonneesExcpetion();
        }
        Puissance4 partie = this.getPartie(idPartie);
        partie.jouerTour(coupDTO.getJoueur(), coupDTO.getColonne());
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
    public UUID rejoindreSalon(UUID idSalon, NomJoueur pseudoJoueur) throws TropDeJoueurException {
        if (salon.get(idSalon).size() < 2) {
            salon.get(idSalon).add(pseudoJoueur.getUsername());
        }else {
            throw new TropDeJoueurException();
        }
        return idSalon;
    }

    @Override
    public boolean getSalon(UUID idSalon) throws SalonInexistantException{
        try {
            ArrayList<String> joueurs = this.salon.get(idSalon);
            if (joueurs.size() == 2){
                if (!parties.containsKey(idSalon)){
                    Puissance4 nouvellePartie = new Puissance4(salon.get(idSalon));
                    parties.put(idSalon,nouvellePartie);
                }
                return true;
            }
            return false;
        }catch (Exception e){
            throw new SalonInexistantException();
        }
    }
    @Override
    public Map<UUID, ArrayList<String>> getSalons() throws SalonInexistantException{
            return this.salon;
    }


}
