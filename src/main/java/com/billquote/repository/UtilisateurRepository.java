package com.billquote.repository;

import com.billquote.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {
    Optional<Utilisateur> findByEmail(String email);
}
