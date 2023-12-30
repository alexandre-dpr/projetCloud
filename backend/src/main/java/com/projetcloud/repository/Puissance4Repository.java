package com.projetcloud.repository;

import com.projetcloud.modele.Puissance4;
import com.projetcloud.modele.Salon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Puissance4Repository extends MongoRepository<Puissance4, String> {
    @Query("{'joueurs': { $elemMatch: { 'username': ?0 } } }")
    List<Puissance4> findByJoueurs(String username);

}
