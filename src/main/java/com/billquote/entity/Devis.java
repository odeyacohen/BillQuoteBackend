package com.billquote.entity;

import com.billquote.model.PdfFile;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "devis")
public class Devis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDevis;

    @Column(nullable = false, length = 200)
    private String titre;

    @Column(precision = 19, scale = 4)
    private BigDecimal montant;

    @Column(nullable = false, updatable = false)
    private Instant dateCreation = Instant.now();

    @Column(nullable = false)
    private Long idUtilisateur;

    @Column(nullable = false)
    private Long idSociete;

    @Column(name = "id_client", nullable = false)
    private Long idClient;

    // 👇 NOUVEAU : lien vers le PDF stocké
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pdf_id")
    private PdfFile pdfFile;

    // --- Getters & Setters ---

    public Long getIdDevis() { return idDevis; }
    public void setIdDevis(Long idDevis) { this.idDevis = idDevis; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public BigDecimal getMontant() { return montant; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }

    public Instant getDateCreation() { return dateCreation; }
    public void setDateCreation(Instant dateCreation) { this.dateCreation = dateCreation; }

    public Long getIdUtilisateur() { return idUtilisateur; }
    public void setIdUtilisateur(Long l) { this.idUtilisateur = l; }

    public Long getIdSociete() { return idSociete; }
    public void setIdSociete(Long l) { this.idSociete = l; }

    public Long getIdClient() { return idClient; }
    public void setIdClient(Long idClient) { this.idClient = idClient; }

    public PdfFile getPdfFile() { return pdfFile; }
    public void setPdfFile(PdfFile pdfFile) { this.pdfFile = pdfFile; }
}
