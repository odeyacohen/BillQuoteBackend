package com.billquote.dto;

import java.math.BigDecimal;
import java.time.Instant;

import com.billquote.entity.Facture.StatutFacture;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FactureDTO {

    private Long idFacture;
    private String numeroFacture;
    private Instant dateFacture;
    private BigDecimal montantFacture;
    private StatutFacture statutFacture;
    private String nomClient; // nouveau champ

    @JsonProperty("id_client") 
    private Long idClient;
    private Long idSociete;
    private Long idUtilisateur;

    private Instant createdAt;
    private Instant updatedAt;

    // Getters & Setters
    public Long getIdFacture() { return idFacture; }
    public void setIdFacture(Long idFacture) { this.idFacture = idFacture; }

    public String getNumeroFacture() { return numeroFacture; }
    public void setNumeroFacture(String numeroFacture) { this.numeroFacture = numeroFacture; }

    public Instant getDateFacture() { return dateFacture; }
    public void setDateFacture(Instant dateFacture) { this.dateFacture = dateFacture; }

    public BigDecimal getMontantFacture() { return montantFacture; }
    public void setMontantFacture(BigDecimal montantFacture) { this.montantFacture = montantFacture; }

    public StatutFacture getStatutFacture() { return statutFacture; }
    public void setStatutFacture(StatutFacture statutFacture) { this.statutFacture = statutFacture; }

    public Long getIdClient() { return idClient; }
    public void setIdClient(Long idClient) { this.idClient = idClient; }

    public Long getIdSociete() { return idSociete; }
    public void setIdSociete(Long idSociete) { this.idSociete = idSociete; }

    public Long getIdUtilisateur() { return idUtilisateur; }
    public void setIdUtilisateur(Long idUtilisateur) { this.idUtilisateur = idUtilisateur; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
 public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }

 
 public String getNomClient() { return nomClient; }
 public void setNomClient(String nomClient) { this.nomClient = nomClient; }
 



}
