package com.billquote.mapper;

import com.billquote.entity.Facture;
import com.billquote.model.FactureRequest;
import com.billquote.model.FactureResponse;

import java.time.Instant;
import java.math.BigDecimal;

public final class FactureMapper {
    private FactureMapper(){}

    public static Facture toEntity(FactureRequest req){
        Facture f = new Facture();
        f.setNumeroFacture(req.getNumeroFacture());
        f.setMontant(req.getMontant() != null ? req.getMontant() : BigDecimal.ZERO);
        f.setDateFacture(Instant.now());
        f.setIdUtilisateur(req.getIdUtilisateur());
        f.setIdSociete(req.getIdSociete());
        f.setIdClient(req.getIdClient());
        return f;
    }

    public static FactureResponse toResponse(Facture f){
        FactureResponse r = new FactureResponse();
        r.setIdFacture(f.getIdFacture());
        r.setNumeroFacture(f.getNumeroFacture());
        r.setMontant(f.getMontant());
        r.setDateFacture(f.getDateFacture());
        r.setStatutFacture(f.getStatutFacture().name());
        r.setIdUtilisateur(f.getIdUtilisateur());
        r.setIdSociete(f.getIdSociete());
        r.setIdClient(f.getIdClient());
        return r;
    }
}
