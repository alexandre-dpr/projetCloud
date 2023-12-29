package com.projetcloud.service;


import com.projetcloud.dto.request.SalonDTO;
import com.projetcloud.dto.response.CoupDTO;
import com.projetcloud.dto.response.NomJoueur;
import com.projetcloud.exceptions.*;
import com.projetcloud.modele.Puissance4;
import com.projetcloud.modele.Salon;
import com.projetcloud.repository.Puissance4Repository;
import com.projetcloud.repository.SalonRepository;
import com.projetcloud.repository.UserRepository;
import com.projetcloud.util.User;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * {@inheritDoc}
 */
@Component
public class Facade implements IFacade{


    private final Puissance4Repository puissance4Repository;

    private final SalonRepository salonRepository;

    private final UserRepository userRepository;



    public Facade(Puissance4Repository puissance4Repository, SalonRepository salonRepository, UserRepository userRepository){
        this.puissance4Repository = puissance4Repository;
        this.salonRepository = salonRepository;
        this.userRepository = userRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Puissance4 jouerCoup(String idPartie, CoupDTO coupDTO) throws MauvaisesCoordonneesExcpetion, PartieInexistanceException, CoupNonAutoriseException, MauvaisTourException, PartieTermineException, JoueurInexistantException {
        if (coupDTO.getColonne()<0){
            throw new MauvaisesCoordonneesExcpetion();
        }
        Puissance4 partie = this.getPartie(idPartie);
        Optional<User> user = userRepository.findByUsername(coupDTO.getJoueur());
        if(!user.isPresent()){
            throw new JoueurInexistantException("Joueur inexistant");
        }
        partie.jouerTour(user.get(), coupDTO.getColonne());
        puissance4Repository.save(partie);
        return partie;
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
    public String creerSalon(String pseudo) throws JoueurInexistantException {
        Salon salon1= new Salon(UUID.randomUUID().toString());
        Optional<User> user = userRepository.findByUsername(pseudo);
        if(!user.isPresent()){
            throw new JoueurInexistantException("Joueur inexistant");
        }
        salon1.getListeJoueur().add(user.get());
        salonRepository.save(salon1);

        return salon1.getId();
    }



    @Override
    public String rejoindreSalon(String idSalon, NomJoueur pseudoJoueur) throws TropDeJoueurException, SalonInexistantException, JoueurInexistantException, DejaDansSalonException {
        Optional<Salon> salon = salonRepository.findById(idSalon);
        System.out.println(pseudoJoueur.getUsername());
        if (!salon.isPresent()){
            throw new SalonInexistantException("Salon inexistant");
        }
        if (salon.get().getListeJoueur().size() >= 2) {
            throw new TropDeJoueurException("Trop de joueur");
        }
        Optional<User> user = userRepository.findByUsername(pseudoJoueur.getUsername());
        if(!user.isPresent()){
            throw new JoueurInexistantException("Joueur inexistant");
        }
        if (user.get().getUsername().equals(salon.get().getListeJoueur().get(0).getUsername())){
            throw new DejaDansSalonException("Déjà dans le salon");
        }
        salon.get().getListeJoueur().add(user.get());
        salonRepository.save(salon.get());
        return idSalon;
    }


    @Override
    public boolean getSalon(String idSalon) throws SalonInexistantException, PartieAlreadyUsedException {
        System.out.println(idSalon);
        Optional<Salon> salon = salonRepository.findById(idSalon);
        if (!salon.isPresent()) {
            Optional<Puissance4> puissance4 = puissance4Repository.findById(idSalon+"salon");
            if (puissance4.isPresent()){
                return true;
            }
           throw new SalonInexistantException("Salon inexistant");
        }
        ArrayList<User> joueurs = salon.get().getListeJoueur();
        if (joueurs.size() == 2){
            Puissance4 nouvellePartie = new Puissance4(joueurs);
            nouvellePartie.setId(idSalon+"salon");
            puissance4Repository.save(nouvellePartie);
            salonRepository.delete(salon.get());
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
