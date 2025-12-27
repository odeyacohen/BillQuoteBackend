package com.billquote.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Size(min = 12, max = 255)
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{12,}$",
            message = "Le mot de passe doit contenir 12 caractères min, 1 minuscule, 1 majuscule, 1 chiffre et 1 caractère spécial"
    )
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

    // (Optionnel) si tu ne l’utilises pas côté back, tu peux le supprimer
    @Size(max = 255)
    private String societeMdp;

    // --------- Cas EMPLOYE / COMPTABLE ---------
    // Tu peux le garder pour compat, mais idéalement il ne doit plus être utilisé côté register
    private Long societeId;
}
