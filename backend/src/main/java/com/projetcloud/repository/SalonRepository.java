package com.projetcloud.repository;

import com.projetcloud.modele.Salon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalonRepository extends MongoRepository<Salon, String> {
    @Query("{'listeJoueur': { $elemMatch: { 'username': ?0 } } }")
    List<Salon> findByListeJoueur(String username);
}
