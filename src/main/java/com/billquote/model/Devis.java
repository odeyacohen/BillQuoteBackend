package com.billquote.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "devis")
public class Devis {

    @Id
    private String idDevis;

    private String titre;
    private double montant;
    private LocalDate dateCreation;

    private String idUtilisateur;
    private String idSociete;

    // Getters and Setters
    public String getIdDevis() {
        return idDevis;
    }

    public void setIdDevis(String idDevis) {
        this.idDevis = idDevis;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getIdSociete() {
        return idSociete;
    }

    public void setIdSociete(String idSociete) {
        this.idSociete = idSociete;
    }
}
