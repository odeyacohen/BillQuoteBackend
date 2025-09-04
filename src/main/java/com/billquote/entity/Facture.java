package com.billquote.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.time.Instant;

@Document(collection = "factures")
@Getter
@Setter
public class Facture {

    @Id
    private String idFacture;   // ObjectId Mongo sous forme de String

    @Indexed(unique = true)
    private String numeroFacture;  // ex: FAC-2025-0001

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal montant;    // montant total TTC

    private Instant dateFacture;   // date d’émission

    private StatutFacture statutFacture = StatutFacture.DRAFT;

    // références logiques (Mongo ↔ SQL)
    private String idUtilisateur;
    private String idSociete;
    private String idClient;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    public enum StatutFacture {
        DRAFT,       // brouillon
        SENT,        // envoyée au client
        PAID,        // payée
        CANCELED     // annulée
    }
}
