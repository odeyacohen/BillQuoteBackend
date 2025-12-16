package com.billquote.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class FactureRequest {
    @NotBlank
    private String numeroFacture;
    @DecimalMin("0.00")
    private BigDecimal montant;
    private Long idUtilisateur;
    private Long idSociete;
    private Long idClient;
    private String nomClient;
    // getters/setters
    public String getNumeroFacture() { return numeroFacture; }
    public void setNumeroFacture(String numeroFacture) { this.numeroFacture = numeroFacture; }
    public BigDecimal getMontant() { return montant; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }
    public Long getIdUtilisateur() { return idUtilisateur; }
    public void setIdUtilisateur(Long idUtilisateur) { this.idUtilisateur = idUtilisateur; }
    public Long getIdSociete() { return idSociete; }
    public void setIdSociete(Long idSociete) { this.idSociete = idSociete; }
    public Long getIdClient() { return idClient; }
    public void setIdClient(Long idClient) { this.idClient = idClient; }
    public String getNomClient() { return nomClient; }
    public void setNomClient(String nomClient) { this.nomClient = nomClient; }
}