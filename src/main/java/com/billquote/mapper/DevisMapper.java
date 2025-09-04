package com.billquote.mapper;

import com.billquote.entity.Devis;
import com.billquote.model.DevisRequest;
import com.billquote.model.DevisResponse;

import java.time.Instant;
import java.math.BigDecimal;

public final class DevisMapper {
    private DevisMapper(){}

    public static Devis toEntity(DevisRequest req){
        Devis d = new Devis();
        d.setTitre(req.getTitre());
        d.setMontant(req.getMontant() != null ? req.getMontant() : BigDecimal.ZERO);
        d.setDateCreation(Instant.now());
        d.setIdUtilisateur(req.getIdUtilisateur());
        d.setIdSociete(req.getIdSociete());
        return d;
    }

    public static DevisResponse toResponse(Devis d){
        DevisResponse r = new DevisResponse();
        r.setIdDevis(d.getIdDevis());
        r.setTitre(d.getTitre());
        r.setMontant(d.getMontant());
        r.setDateCreation(d.getDateCreation());
        r.setIdUtilisateur(d.getIdUtilisateur());
        r.setIdSociete(d.getIdSociete());
        return r;
    }
}
