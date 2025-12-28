package com.billquote.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserDTO {

    @NotBlank
    @Email
    @Size(max = 255)
    private String email;

    @NotBlank
    @Size(max = 100)
    private String nom;

    @Size(max = 100)
    private String prenom;

    @Size(max = 30)
    private String tel;

    // ✅ uniquement EMPLOYE ou COMPTABLE
    @NotBlank
    @Pattern(regexp = "^(EMPLOYE|COMPTABLE)$", message = "role doit être EMPLOYE ou COMPTABLE")
    private String role;

    @NotBlank
    @Size(min = 12, max = 255)
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{12,}$",
            message = "Le mot de passe doit contenir 12 caractères min, 1 minuscule, 1 majuscule, 1 chiffre et 1 caractère spécial"
    )
    private String motDePasse;
}
