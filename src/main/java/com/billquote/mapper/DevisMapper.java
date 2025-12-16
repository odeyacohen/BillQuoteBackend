package com.billquote.mapper;

import com.billquote.entity.Devis;
import com.billquote.model.DevisRequest;
import com.billquote.model.DevisResponse;

import java.math.BigDecimal;
import java.time.Instant;

public final class DevisMapper {
    private DevisMapper() {}

    public static Devis toEntity(DevisRequest req) {
        Devis d = new Devis();
        d.setTitre(req.getTitre());
        d.setMontant(req.getMontant() != null ? req.getMontant() : BigDecimal.ZERO);
        d.setDateCreation(Instant.now());

        // ⚠️ si tes DTO ont des String → pense à parser en Long
        if (req.getIdUtilisateur() != null) {
            d.setIdUtilisateur(Long.valueOf(req.getIdUtilisateur()));
        }
        if (req.getIdSociete() != null) {
            d.setIdSociete(Long.valueOf(req.getIdSociete()));
        }
        return d;
    }

    public static DevisResponse toResponse(Devis d) {
        DevisResponse r = new DevisResponse();
        r.setIdDevis(d.getIdDevis());
        r.setTitre(d.getTitre());
        r.setMontant(d.getMontant());
        r.setDateCreation(d.getDateCreation());

        // ⚠️ si ton DevisResponse attend encore des String
        if (d.getIdUtilisateur() != null) {
            r.setIdUtilisateur(Long.valueOf(d.getIdUtilisateur()));
        }
        if (d.getIdSociete() != null) {
            r.setIdSociete(Long.valueOf(d.getIdSociete()));
        }
        return r;
    }
}
