package com.billquote.dto;

public class DevisDTO {

    private String idDevis;
    private String titre;
    private double montant;
    private String dateCreation;
    private String idUtilisateur;
    private String idSociete;

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

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
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
