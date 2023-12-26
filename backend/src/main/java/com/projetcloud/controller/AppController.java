package com.projetcloud.controller;

import com.projetcloud.dto.response.CoupDTO;
import com.projetcloud.dto.response.NomJoueur;
import com.projetcloud.exceptions.*;
import com.projetcloud.service.Facade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;


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
    @PostMapping("/partie/{idPartie}/coup")
    public ResponseEntity<?> jouerCoup(@PathVariable String idPartie, @RequestBody CoupDTO coupDTO) throws CoupNonAutoriseException, PartieInexistanceException, MauvaisTourException, MauvaisesCoordonneesExcpetion, PartieTermineException {
            facade.jouerCoup(idPartie, coupDTO);
            return ResponseEntity.ok().build();
    }


    @GetMapping("/partie/{idPartie}")
    public ResponseEntity<?> getPartie(@PathVariable String idPartie) {
        try {
            return ResponseEntity.ok(facade.getPartie(idPartie));
        } catch (PartieInexistanceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Partie introuvable");
        }
    }

    @GetMapping("/partie")
    public ResponseEntity<?> creerPartie(@RequestParam String idPartie, @RequestParam ArrayList<String> listeJoueur) throws PartieAlreadyUsedException {
        return ResponseEntity.ok(facade.creerPartie(idPartie, listeJoueur));
    }

    @PostMapping("/salon")
    public ResponseEntity<?> creerSalon(@RequestBody NomJoueur nomJoueur) {
        return ResponseEntity.ok(facade.creerSalon(nomJoueur.getUsername()));
    }
    @GetMapping("/salon")
    public ResponseEntity<?> getSalon() throws SalonInexistantException {
        return ResponseEntity.ok(facade.getSalons());
    }

    @PostMapping("/salon/{idSalon}")
    public ResponseEntity<?> rejoindreSalon(@PathVariable String idSalon, @RequestBody NomJoueur nomJoueur) throws TropDeJoueurException, SalonInexistantException {
        return ResponseEntity.ok(facade.rejoindreSalon(idSalon, nomJoueur));
    }

    @GetMapping("/salon/{idSalon}")
    public ResponseEntity<?> getSalon(@PathVariable String idSalon) throws SalonInexistantException, PartieAlreadyUsedException {
        return ResponseEntity.ok(facade.getSalon(idSalon));
    }

}
