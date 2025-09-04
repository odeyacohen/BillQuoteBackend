package com.billquote.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "client")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Long idClient;

    @Column(name = "nom_client", nullable = false, length = 100)
    private String nomClient;

    @Column(name = "prenom_client", length = 100)
    private String prenomClient;

    @Column(name = "nom_soc_client", length = 150)
    private String nomSocClient;

    @Column(name = "mail_client", length = 180, unique = true)
    private String mailClient;

    @Column(name = "tel_client", length = 25)
    private String telClient;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
}
