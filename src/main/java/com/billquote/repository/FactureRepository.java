package com.billquote.repository;

import com.billquote.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FactureRepository extends JpaRepository<Facture, Long> {

    // ✅ Spring Data génère la requête automatiquement (pas de SQL dans ton code)
    Optional<Facture> findTopByOrderByIdFactureDesc();

    boolean existsByNumeroFacture(String numeroFacture);
}
