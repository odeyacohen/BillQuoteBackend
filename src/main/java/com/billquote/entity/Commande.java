package com.billquote.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "commande")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @NoArgsConstructor
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_commande")
    private Long idCommande;

    @Column(name = "description_com", length = 255)
    private String descriptionCom;

    @Column(name = "quantite_com", nullable = false)
    private Integer quantiteCom;

    @Column(name = "prix_unitaire", precision = 18, scale = 2)
    private BigDecimal prixUnitaire;

    @Column(name = "prix_tva", precision = 5, scale = 2)
    private BigDecimal prixTva;        // taux TVA (ex: 20.00)

    @Column(name = "prix_ttc", precision = 18, scale = 2)
    private BigDecimal prixTtc;        // total TTC de la ligne

    // rattachements SQL
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client", foreignKey = @ForeignKey(name = "fk_commande_client"))
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_soc", foreignKey = @ForeignKey(name = "fk_commande_societe"))
    private Societe societe;

    // références vers documents stockés en Mongo
    @Column(name = "id_devis_mongo", length = 40)
    private String idDevisMongo;

    @Column(name = "id_facture_mongo", length = 40)
    private String idFactureMongo;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
}
