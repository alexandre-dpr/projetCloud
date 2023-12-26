package com.projetcloud.repository;

import com.projetcloud.modele.Salon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalonRepository extends MongoRepository<Salon, String> {
}
