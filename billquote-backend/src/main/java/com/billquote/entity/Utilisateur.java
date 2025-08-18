package com.billquote.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Utilisateur {
    @Id
    private String mail_utilisateur;
    private String nom_utilisateur;
    private String prenom_utilisateur;
    private String tel_utilisateur;
    private String mot_de_passe;
    private String role; // SOCIETE, EMPLOYE
}
