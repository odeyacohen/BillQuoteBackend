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

@Document(collection = "devis")
@Getter
@Setter
public class Devis {

    @Id
    private String idDevis;            // ObjectId sous forme de String (ok pour Mongo)

    @Indexed                     // utile pour chercher par titre
    private String titre;

    @Field(targetType = FieldType.DECIMAL128)   // évite les erreurs d’arrondi (double)
    private BigDecimal montant;

    @CreatedDate                 // auto-rempli par Mongo Auditing
    private Instant dateCreation;

    @LastModifiedDate            // auto-maj
    private Instant dateMaj;

    // références “logiques” (pas de @DBRef vers MySQL)
    private String idUtilisateur;
    private String idSociete;
}
