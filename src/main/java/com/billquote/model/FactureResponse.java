package com.billquote.model;

import java.math.BigDecimal;
import java.time.Instant;

public class FactureResponse {
    private Long idFacture;
    private String numeroFacture;
    private BigDecimal montant;
    private Instant dateFacture;
    private String statutFacture;
    private Long idUtilisateur;
    private Long idSociete;
    private Long idClient;
    private String nomClient;
	
	public void setIdFacture(Long long1) {
		this.idFacture = long1;
	}
	public String getNumeroFacture() {
		return numeroFacture;
	}
	public void setNumeroFacture(String numeroFacture) {
		this.numeroFacture = numeroFacture;
	}
	public BigDecimal getMontant() {
		return montant;
	}
	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}
	public Instant getDateFacture() {
		return dateFacture;
	}
	public void setDateFacture(Instant dateFacture) {
		this.dateFacture = dateFacture;
	}
	public String getStatutFacture() {
		return statutFacture;
	}
	public void setStatutFacture(String statutFacture) {
		this.statutFacture = statutFacture;
	}
	public Long getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public Long getIdSociete() {
		return idSociete;
	}
	public void setIdSociete(Long idSociete) {
		this.idSociete = idSociete;
	}
	public Long getIdClient() {
		return idClient;
	}
	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}
	 public String getNomClient() { return nomClient; }
	    public void setNomClient(String nomClient) { this.nomClient = nomClient; }

    // getters/setters
   
}