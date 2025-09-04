package com.billquote.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "societe")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @NoArgsConstructor
public class Societe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_soc")
    private Long idSoc;

    @Column(name = "nom_soc", nullable = false, length = 150)
    private String nomSoc;

    @Column(name = "tel_soc", length = 25)
    private String telSoc;

    @Column(name = "mail_soc", length = 180, unique = true)
    private String mailSoc;

    @Column(name = "mdp_soc", length = 255)
    private String mdpSoc; // si utilisé : à stocker hashé

    // adresse optionnelle
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_adresse", foreignKey = @ForeignKey(name = "fk_societe_adresse"))
    private Adresse adresse;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
}
