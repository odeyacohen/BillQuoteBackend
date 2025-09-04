package com.billquote.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class DevisRequest {
    @NotBlank @Size(max = 200)
    private String titre;
    @NotNull @DecimalMin("0.00")
    private BigDecimal montant;
    private String idUtilisateur;
    private String idSociete;

    // getters/setters
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public BigDecimal getMontant() { return montant; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }
    public String getIdUtilisateur() { return idUtilisateur; }
    public void setIdUtilisateur(String idUtilisateur) { this.idUtilisateur = idUtilisateur; }
    public String getIdSociete() { return idSociete; }
    public void setIdSociete(String idSociete) { this.idSociete = idSociete; }
}
