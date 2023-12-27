package com.projetcloud.service;


import com.projetcloud.dto.request.SalonDTO;
import com.projetcloud.dto.response.CoupDTO;
import com.projetcloud.dto.response.NomJoueur;
import com.projetcloud.exceptions.*;
import com.projetcloud.modele.Puissance4;
import com.projetcloud.modele.Salon;
import com.projetcloud.repository.Puissance4Repository;
import com.projetcloud.repository.SalonRepository;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * {@inheritDoc}
 */
@Component
public class Facade implements IFacade{


    private final Puissance4Repository puissance4Repository;

    private final SalonRepository salonRepository;



    public Facade(Puissance4Repository puissance4Repository, SalonRepository salonRepository){
        this.puissance4Repository = puissance4Repository;
        this.salonRepository = salonRepository;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Puissance4 jouerCoup(String idPartie, CoupDTO coupDTO) throws MauvaisesCoordonneesExcpetion, PartieInexistanceException, CoupNonAutoriseException, MauvaisTourException, PartieTermineException {
        if (coupDTO.getColonne()<0){
            throw new MauvaisesCoordonneesExcpetion();
        }
        Puissance4 partie = this.getPartie(idPartie);
        partie.jouerTour(coupDTO.getJoueur(), coupDTO.getColonne());
        puissance4Repository.save(partie);
        return partie;
    }

    @Override
    public Puissance4 creerPartie(String id, ArrayList<String> listeJoueur) throws PartieAlreadyUsedException {
        Optional<Puissance4> puissance4 = puissance4Repository.findById(id);
        if (puissance4.isPresent()){
            throw new PartieAlreadyUsedException("Partie existante");
        }
        Puissance4 nouvellePartie = new Puissance4(listeJoueur);
        nouvellePartie.setId(id);
        puissance4Repository.save(nouvellePartie);
        return nouvellePartie;
    }

   @Override
    public Puissance4 getPartie(String idPartie) throws PartieInexistanceException{
       Optional<Puissance4> puissance4 = puissance4Repository.findById(idPartie+"salon");
       if (!puissance4.isPresent()){
           throw new PartieInexistanceException("Partie Inexistante");
       }
       return puissance4.get();
    }

    @Override
    public String creerSalon(String pseudo) {
        Salon salon1= new Salon(UUID.randomUUID().toString());
        salon1.getListeJoueur().add(pseudo);
        salonRepository.save(salon1);

        return salon1.getId();
    }



    @Override
    public String rejoindreSalon(String idSalon, NomJoueur pseudoJoueur) throws TropDeJoueurException, SalonInexistantException {
        Optional<Salon> salon = salonRepository.findById(idSalon);
        System.out.println(pseudoJoueur.getUsername());
        if (!salon.isPresent()){
            throw new SalonInexistantException("Salon inexistant");
        }
        if (salon.get().getListeJoueur().size() >= 2) {
            throw new TropDeJoueurException("Trop de joueur");
        }
        salon.get().getListeJoueur().add(pseudoJoueur.getUsername());
        salonRepository.save(salon.get());
        return idSalon;
    }


    @Override
    public boolean getSalon(String idSalon) throws SalonInexistantException, PartieAlreadyUsedException {
        System.out.println(idSalon);
        Optional<Salon> salon = salonRepository.findById(idSalon);
        System.out.println("test");
        if (!salon.isPresent()) {
           throw new SalonInexistantException("Salon inexistant");
        }
        ArrayList<String> joueurs = salon.get().getListeJoueur();
        System.out.println(joueurs);
        if (joueurs.size() == 2){
            System.out.println("test2");
            Optional<Puissance4> puissance4 = puissance4Repository.findById(idSalon+"salon");
            System.out.println("test3");
            if (puissance4.isPresent()){
                return true;
            }
            Puissance4 nouvellePartie = new Puissance4(joueurs);
            nouvellePartie.setId(idSalon+"salon");
            puissance4Repository.save(nouvellePartie);

            return true;
        }
        return false;

    }
    @Override
    public ArrayList<SalonDTO> getSalons() throws SalonInexistantException{
        List<Salon> salons= salonRepository.findAll();
        if (salons.isEmpty()){
            throw new SalonInexistantException("Aucun salon");
        }
        List<Puissance4> puissance4 = puissance4Repository.findAll();
        System.out.println(puissance4);
        ArrayList<SalonDTO> salonDTOS = new ArrayList<>();
        salons.forEach(salon1 -> {
            SalonDTO salonDTO = new SalonDTO();
            salonDTO.setId(salon1.getId());
            salonDTO.setListeJoueur(salon1.getListeJoueur());
            salonDTOS.add(salonDTO);
        });
        return salonDTOS;
    }


}
