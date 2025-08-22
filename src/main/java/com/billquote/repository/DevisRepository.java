package com.billquote.repository;

import com.billquote.entity.Devis;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DevisRepository extends MongoRepository<Devis, String> {
}