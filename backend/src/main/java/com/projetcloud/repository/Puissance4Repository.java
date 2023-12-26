package com.projetcloud.repository;

import com.projetcloud.modele.Puissance4;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Puissance4Repository extends MongoRepository<Puissance4, String> {

}
