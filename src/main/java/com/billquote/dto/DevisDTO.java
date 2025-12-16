package com.billquote.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Lob;

import java.math.BigDecimal;
import java.time.Instant;

public class DevisDTO {

    private Long idDevis;
    private String titre;
    private BigDecimal montant;
    private Instant dateCreation;
    private Long idUtilisateur;
    private Long idSociete;

    @JsonProperty("id_client")   // <--- JSON: "id_client"
    private Long idClient;

  
    
    private String nomClient;

    public Long getIdDevis() { return idDevis; }
    public void setIdDevis(Long long1) { this.idDevis = long1; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public BigDecimal getMontant() { return montant; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }

    public Instant getDateCreation() { return dateCreation; }
    public void setDateCreation(Instant dateCreation) { this.dateCreation = dateCreation; }

    public Long getIdUtilisateur() { return idUtilisateur; }
    public void setIdUtilisateur(Long long1) { this.idUtilisateur = long1; }

    public Long getIdSociete() { return idSociete; }
    public void setIdSociete(Long long1) { this.idSociete = long1; }

    public Long getIdClient() { return idClient; }
    public void setIdClient(Long idClient) { this.idClient = idClient; }

    public String getNomClient() { return nomClient; }
    public void setNomClient(String nomClient) { this.nomClient = nomClient; }
}
