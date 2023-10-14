package com.projetcloud.controller;

import com.projetcloud.exceptions.MauvaisesCoordonneesExcpetion;
import com.projetcloud.service.Facade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/{idPartie}")
@RestController
public class AppController {

    private final Facade facade;

    //TODO : initialiser le bean
    public AppController(Facade facade) {
        this.facade = facade;
    }

    /**
     * Jouer un coup
     *
     * @param x
     * @param y
     * @return
     */
    @PostMapping("/")
    public ResponseEntity<?> jouerCoup(@PathVariable String idPartie, int x, int y){
        try {
           facade.jouerCoup(idPartie,x,y);
           return ResponseEntity.ok().build();
        } catch (MauvaisesCoordonneesExcpetion e) {
            return ResponseEntity.badRequest().build();
        }

    }

    /**
     * Obtenir une partie et son état
     * @param idPartie : i de la partie
     * @return : la partie en JSON
     */
    @GetMapping("/")
    public ResponseEntity<?> getPartie(@PathVariable String idPartie){
      // TODO : add try/catch après création du modèle
            return ResponseEntity.ok(facade.getPartie(idPartie));

    }
}
