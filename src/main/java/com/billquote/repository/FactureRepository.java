package com.billquote.repository;

import com.billquote.model.DevisResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FactureRepository extends MongoRepository<DevisResponse, String> {
}
