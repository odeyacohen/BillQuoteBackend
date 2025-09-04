package com.billquote.model;

import java.math.BigDecimal;
import java.time.Instant;

public class DevisResponse {
    private String idDevis;
    private String titre;
    private BigDecimal montant;
    private Instant dateCreation;
    private String idUtilisateur;
    private String idSociete;

    // getters/setters
    public String getIdDevis() { return idDevis; }
    public void setIdDevis(String idDevis) { this.idDevis = idDevis; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public BigDecimal getMontant() { return montant; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }
    public Instant getDateCreation() { return dateCreation; }
    public void setDateCreation(Instant dateCreation) { this.dateCreation = dateCreation; }
    public String getIdUtilisateur() { return idUtilisateur; }
    public void setIdUtilisateur(String idUtilisateur) { this.idUtilisateur = idUtilisateur; }
    public String getIdSociete() { return idSociete; }
    public void setIdSociete(String idSociete) { this.idSociete = idSociete; }
}
