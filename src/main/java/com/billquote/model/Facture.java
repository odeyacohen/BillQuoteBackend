package com.billquote.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "factures")
public class Facture {

    @Id
    private String idFacture;
    private String numeroFacture;
    private LocalDate dateFacture;
    private double montantFacture;
    private String statutFacture;

    // Getters and Setters
    public String getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(String idFacture) {
        this.idFacture = idFacture;
    }

    public String getNumeroFacture() {
        return numeroFacture;
    }

    public void setNumeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    public LocalDate getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(LocalDate dateFacture) {
        this.dateFacture = dateFacture;
    }

    public double getMontantFacture() {
        return montantFacture;
    }

    public void setMontantFacture(double montantFacture) {
        this.montantFacture = montantFacture;
    }

    public String getStatutFacture() {
        return statutFacture;
    }

    public void setStatutFacture(String statutFacture) {
        this.statutFacture = statutFacture;
    }
}
