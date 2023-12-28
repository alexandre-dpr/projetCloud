package com.projetcloud.controller;

import com.projetcloud.dto.response.CoupDTO;
import com.projetcloud.dto.response.NomJoueur;
import com.projetcloud.exceptions.*;
import com.projetcloud.service.Facade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class AppController {

    private final Facade facade;

    public AppController(Facade facade) {
        this.facade = facade;
    }

    /**
     * Jouer un coup
     *
     * @return
     */
    @PostMapping("${api.version}/partie/{idPartie}/coup")
    public ResponseEntity<?> jouerCoup(@PathVariable String idPartie, @RequestBody CoupDTO coupDTO) throws CoupNonAutoriseException, PartieInexistanceException, MauvaisTourException, MauvaisesCoordonneesExcpetion, PartieTermineException, JoueurInexistantException {
        facade.jouerCoup(idPartie, coupDTO);
        return ResponseEntity.ok().build();
    }


    @GetMapping("${api.version}/partie/{idPartie}")
    public ResponseEntity<?> getPartie(@PathVariable String idPartie) {
        try {
            return ResponseEntity.ok(facade.getPartie(idPartie));
        } catch (PartieInexistanceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Partie introuvable");
        }
    }



    @PostMapping("${api.version}/salon")
    public ResponseEntity<?> creerSalon(@RequestBody NomJoueur nomJoueur) throws JoueurInexistantException {
        return ResponseEntity.ok(facade.creerSalon(nomJoueur.getUsername()));
    }

    @GetMapping("${api.version}/salon")
    public ResponseEntity<?> getSalon() throws SalonInexistantException {
        return ResponseEntity.ok(facade.getSalons());
    }

    @PostMapping("${api.version}/salon/{idSalon}")
    public ResponseEntity<?> rejoindreSalon(@PathVariable String idSalon, @RequestBody NomJoueur nomJoueur) throws TropDeJoueurException, SalonInexistantException, JoueurInexistantException, DejaDansSalonException {
        return ResponseEntity.ok(facade.rejoindreSalon(idSalon, nomJoueur));
    }

    @GetMapping("${api.version}/salon/{idSalon}")
    public ResponseEntity<?> getSalon(@PathVariable String idSalon) throws SalonInexistantException, PartieAlreadyUsedException {
        return ResponseEntity.ok(facade.getSalon(idSalon));
    }

}
