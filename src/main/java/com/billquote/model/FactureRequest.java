package com.billquote.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class FactureRequest {
    @NotBlank
    private String numeroFacture;
    @DecimalMin("0.00")
    private BigDecimal montant;
    private String idUtilisateur;
    private String idSociete;
    private String idClient;

    // getters/setters
    public String getNumeroFacture() { return numeroFacture; }
    public void setNumeroFacture(String numeroFacture) { this.numeroFacture = numeroFacture; }
    public BigDecimal getMontant() { return montant; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }
    public String getIdUtilisateur() { return idUtilisateur; }
    public void setIdUtilisateur(String idUtilisateur) { this.idUtilisateur = idUtilisateur; }
    public String getIdSociete() { return idSociete; }
    public void setIdSociete(String idSociete) { this.idSociete = idSociete; }
    public String getIdClient() { return idClient; }
    public void setIdClient(String idClient) { this.idClient = idClient; }
}
