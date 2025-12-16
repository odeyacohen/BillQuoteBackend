package com.billquote.entity;

import com.billquote.model.PdfFile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "facture")
@Getter
@Setter
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrément MySQL
    private Long idFacture;

    @Column(name = "numero_facture", nullable = true, unique = true, length = 50)
    private String numeroFacture;  // ex: FAC-2025-0001

    @Column(precision = 19, scale = 4, nullable = true)
    private BigDecimal montant;    // montant total TTC

    @Column(name = "date_facture", nullable = true)
    private Instant dateFacture;   // date d’émission

    @Enumerated(EnumType.STRING)
    @Column(name = "statut_facture", nullable = true, length = 8)
    private StatutFacture statutFacture = StatutFacture.SENT;

    @Column(name = "id_utilisateur", nullable = true)
    private Long idUtilisateur;

    @Column(name = "id_societe", nullable = true)
    private Long idSociete;

    @Column(name = "id_client", nullable = true)
    private Long idClient;

    @Column(name = "created_at", updatable = false, nullable = true)
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at", nullable = true )
    private Instant updatedAt;

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = Instant.now();
    }

    // 👇 NOUVEAU : lien vers le PDF stocké
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pdf_id")
    private PdfFile pdfFile;

    public enum StatutFacture {
        SENT,       // brouillon
        PAID,       // payée
        DELETE      // annulée
    }
}
