package com.billquote.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.Instant;

public class DevisResponse {

    private Long idDevis;
    private String titre;
    private BigDecimal montant;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Instant dateCreation;

    private Long idUtilisateur;
    private Long idSociete;

    // 👇 nom du client renvoyé au front
    private String nomClient;

    public Long getIdDevis() { return idDevis; }
    public void setIdDevis(Long idDevis) { this.idDevis = idDevis; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public BigDecimal getMontant() { return montant; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }

    public Instant getDateCreation() { return dateCreation; }
    public void setDateCreation(Instant dateCreation) { this.dateCreation = dateCreation; }

    public Long getIdUtilisateur() { return idUtilisateur; }
    public void setIdUtilisateur(Long idUtilisateur) { this.idUtilisateur = idUtilisateur; }

    public Long getIdSociete() { return idSociete; }
    public void setIdSociete(Long idSociete) { this.idSociete = idSociete; }

    public String getNomClient() { return nomClient; }
    public void setNomClient(String nomClient) { this.nomClient = nomClient; }
}
