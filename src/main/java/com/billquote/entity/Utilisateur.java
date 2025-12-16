package com.billquote.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "utilisateur",
	    uniqueConstraints = @UniqueConstraint(columnNames = "email"))
	
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder


public class Utilisateur {
	@ManyToOne
	@JoinColumn(name = "id_soc")
	private Societe societe;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    
    private String email;

    private String nom;
    private String prenom;
    private String motDePasse;
    private String tel;
    private String role;

}
