package com.billquote.model;

import java.math.BigDecimal;
import java.time.Instant;

public class FactureResponse {
    private String idFacture;
    private String numeroFacture;
    private BigDecimal montant;
    private Instant dateFacture;
    private String statutFacture;
    private String idUtilisateur;
    private String idSociete;
    private String idClient;

    // getters/setters
    public String getIdFacture() { return idFacture; }
    public void setIdFacture(String idFacture) { this.idFacture = idFacture; }
    public String getNumeroFacture() { return numeroFacture; }
    public void setNumeroFacture(String numeroFacture) { this.numeroFacture = numeroFacture; }
    public BigDecimal getMontant() { return montant; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }
    public Instant getDateFacture() { return dateFacture; }
    public void setDateFacture(Instant dateFacture) { this.dateFacture = dateFacture; }
    public String getStatutFacture() { return statutFacture; }
    public void setStatutFacture(String statutFacture) { this.statutFacture = statutFacture; }
    public String getIdUtilisateur() { return idUtilisateur; }
    public void setIdUtilisateur(String idUtilisateur) { this.idUtilisateur = idUtilisateur; }
    public String getIdSociete() { return idSociete; }
    public void setIdSociete(String idSociete) { this.idSociete = idSociete; }
    public String getIdClient() { return idClient; }
    public void setIdClient(String idClient) { this.idClient = idClient; }
}
