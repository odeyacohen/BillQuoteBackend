package com.billquote.service;

import com.billquote.dto.CreateUserDTO;
import com.billquote.entity.Societe;
import com.billquote.entity.Utilisateur;
import com.billquote.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class SocieteUserServiceImpl implements SocieteUserService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUserForMySociete(CreateUserDTO dto) {

        // ✅ récupère l'email depuis l'utilisateur authentifié (JWT)
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Non authentifié");
        }

        String societeEmail = auth.getName().trim().toLowerCase();

        Utilisateur societeUser = utilisateurRepository.findByEmail(societeEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Compte société introuvable"));

        if (!"SOCIETE".equalsIgnoreCase(societeUser.getRole())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Accès réservé aux sociétés");
        }

        Societe soc = societeUser.getSociete();
        if (soc == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Aucune société associée au compte");
        }

        String newEmail = dto.getEmail().trim().toLowerCase();
        if (utilisateurRepository.existsByEmail(newEmail)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email déjà utilisé");
        }

        Utilisateur u = new Utilisateur();
        u.setEmail(newEmail);
        u.setNom(dto.getNom());
        u.setPrenom(dto.getPrenom());
        u.setTel(dto.getTel());
        u.setRole(dto.getRole()); // EMPLOYE ou COMPTABLE
        u.setMotDePasse(passwordEncoder.encode(dto.getMotDePasse()));

        // ✅ rattachement forcé à la société du compte connecté
        u.setSociete(soc);

        utilisateurRepository.save(u);
    }

	
}
