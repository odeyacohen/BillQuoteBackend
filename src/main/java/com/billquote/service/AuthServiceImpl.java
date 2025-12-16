package com.billquote.service;

import com.billquote.dto.AuthRequest;
import com.billquote.dto.AuthResponse;
import com.billquote.dto.RegisterRequest;
import com.billquote.entity.Societe;
import com.billquote.entity.Utilisateur;
import com.billquote.repository.SocieteRepository;
import com.billquote.repository.UtilisateurRepository;
import com.billquote.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final SocieteRepository societeRepository;

    @Override
    public AuthResponse register(RegisterRequest request) {

        // on garde clairement le mot de passe "brut" pour s'en servir aussi pour la société
        String rawPassword = request.getMotDePasse();

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(request.getNom());
        utilisateur.setPrenom(request.getPrenom());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setMotDePasse(passwordEncoder.encode(rawPassword));   // mdp UTILISATEUR (hashé)
        utilisateur.setRole(request.getRole());
        utilisateur.setTel(request.getTel());                             // tel UTILISATEUR

        // CAS 1 : SOCIETE
        if ("SOCIETE".equalsIgnoreCase(request.getRole())) {

            Societe soc = new Societe();
            soc.setNomSoc(request.getSocieteNom());
            soc.setMailSoc(request.getSocieteEmail());

            // téléphone de la société :
            // - si tu as un champ dédié societeTel, utilise-le
            // - sinon tu peux reprendre le tel utilisateur
            String telSoc = request.getSocieteTel() != null
                    ? request.getSocieteTel()
                    : request.getTel();
            soc.setTelSoc(telSoc);

            // mdp de la société : même base que l'utilisateur (hashé aussi)
            soc.setMdpSoc(passwordEncoder.encode(rawPassword));

            // si tu as l'adresse :
            // Adresse adr = ...
            // soc.setAdresse(adr);

            soc = societeRepository.save(soc);

            // lie l'utilisateur à la société créée
            utilisateur.setSociete(soc);
        }

        // CAS 2 : EMPLOYE / COMPTABLE
        else if ("EMPLOYE".equalsIgnoreCase(request.getRole())
              || "COMPTABLE".equalsIgnoreCase(request.getRole())) {

            Societe soc = societeRepository.findById(request.getSocieteId())
                    .orElseThrow(() -> new RuntimeException("Société introuvable"));
            utilisateur.setSociete(soc);
        }

        utilisateurRepository.save(utilisateur);

        String jwt = jwtUtil.generateToken(utilisateur.getEmail());
        return new AuthResponse(jwt);
    }


    @Override
    public AuthResponse login(AuthRequest request) {
        final String email = request.getEmail().trim().toLowerCase();

        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, request.getMotDePasse())
            );
        } catch (org.springframework.security.authentication.BadCredentialsException e) {
            throw new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.UNAUTHORIZED, "Email ou mot de passe incorrect");
        } catch (org.springframework.security.authentication.DisabledException |
                 org.springframework.security.authentication.LockedException e) {
            throw new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.FORBIDDEN, e.getMessage());
        }

        Utilisateur user = utilisateurRepository.findByEmail(email)
            .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                org.springframework.http.HttpStatus.NOT_FOUND, "Utilisateur introuvable"));

        String jwt = jwtUtil.generateToken(user.getEmail()); // éventuellement: inclure user.getId(), user.getRole()

        return new AuthResponse(jwt);
    }

}
