package com.billquote.repository;

import com.billquote.model.Facture;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FactureRepository extends MongoRepository<Facture, String> {
}
