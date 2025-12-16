package com.billquote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.billquote.entity.Societe;

public interface SocieteRepository extends JpaRepository<Societe, Long> {
}
