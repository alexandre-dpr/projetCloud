package com.projetcloud.controller;

import com.projetcloud.dto.request.SalonDTO;
import com.projetcloud.dto.response.CoupDTO;
import com.projetcloud.dto.response.NomJoueur;
import com.projetcloud.exceptions.*;
import com.projetcloud.modele.Puissance4;
import com.projetcloud.service.Facade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@SecurityRequirement(name = "Bearer Authentification")
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
    @Operation(summary = "Permet de jouer un coup")
    @PostMapping("${api.version}/partie/{idPartie}/coup")
    public ResponseEntity<Puissance4> jouerCoup(@PathVariable String idPartie, @RequestBody CoupDTO coupDTO) throws CoupNonAutoriseException, PartieInexistanceException, MauvaisTourException, MauvaisesCoordonneesExcpetion, PartieTermineException, JoueurInexistantException {
        facade.jouerCoup(idPartie, coupDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Récupère une partie")
    @GetMapping("${api.version}/partie/{idPartie}")
    public ResponseEntity<Puissance4> getPartie(@PathVariable String idPartie) throws PartieInexistanceException {

        return ResponseEntity.ok(facade.getPartie(idPartie));

    }


    @Operation(summary = "Créé un salon")
    @PostMapping("${api.version}/salon")
    public ResponseEntity<String> creerSalon(@RequestBody NomJoueur nomJoueur) throws JoueurInexistantException {

            return ResponseEntity.ok(facade.creerSalon(nomJoueur.getUsername()));

    }

    @Operation(summary = "Récupère tous les salons")
    @GetMapping("${api.version}/salon")
    public ResponseEntity<ArrayList<SalonDTO>> getSalon() throws SalonInexistantException {
        return ResponseEntity.ok(facade.getSalons());
    }

    @Operation(summary = "Rejoin un salon")
    @PostMapping("${api.version}/salon/{idSalon}")
    public ResponseEntity<String> rejoindreSalon(@PathVariable String idSalon, @RequestBody NomJoueur nomJoueur) throws TropDeJoueurException, SalonInexistantException, JoueurInexistantException, DejaDansSalonException {
        return ResponseEntity.ok(facade.rejoindreSalon(idSalon, nomJoueur));
    }

    @Operation(summary = "Récupère un salon")
    @GetMapping("${api.version}/salon/{idSalon}")
    public ResponseEntity<Boolean> getSalon(@PathVariable String idSalon) throws SalonInexistantException, PartieAlreadyUsedException {
        return ResponseEntity.ok(facade.getSalon(idSalon));
    }

}
