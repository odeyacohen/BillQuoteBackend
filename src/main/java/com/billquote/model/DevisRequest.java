package com.billquote.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.Instant;

public class DevisRequest {

    private String titre;
    private BigDecimal montant;

    private Long idUtilisateur;
    private Long idSociete;

    // 👇 nouveau : le nom du client que l’app Android enverra
    private String nomClient;

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public BigDecimal getMontant() { return montant; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }

    public Long getIdUtilisateur() { return idUtilisateur; }
    public void setIdUtilisateur(Long idUtilisateur) { this.idUtilisateur = idUtilisateur; }

    public Long getIdSociete() { return idSociete; }
    public void setIdSociete(Long idSociete) { this.idSociete = idSociete; }

    public String getNomClient() { return nomClient; }
    public void setNomClient(String nomClient) { this.nomClient = nomClient; }
}
