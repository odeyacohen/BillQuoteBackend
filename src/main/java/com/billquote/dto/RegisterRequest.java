package com.billquote.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {

    // Infos de connexion
    @NotBlank
    @Email
    @Size(max = 255)
    private String email;

    @NotBlank
    @Size(min = 6, max = 255)
    private String motDePasse;

    // Infos utilisateur
    @Size(max = 100)
    private String nom;

    @Size(max = 100)
    private String prenom;

    @Size(max = 30)
    private String tel;

    // Rôle: "SOCIETE", "EMPLOYE", "COMPTABLE"
    @NotBlank
    private String role;

    // --------- Cas SOCIETE ---------
    @Size(max = 150)
    private String societeNom;

    @Email
    @Size(max = 255)
    private String societeEmail;

    @Size(max = 30)
    private String societeTel;

    @Size(max = 255)
    private String societeMdp;

    // --------- Cas EMPLOYE / COMPTABLE ---------
    private Long societeId;
}
