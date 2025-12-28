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
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

        // ✅ Register réservé aux SOCIETES
        if (!"SOCIETE".equalsIgnoreCase(request.getRole())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Seule une société peut s'inscrire. Les employés/comptables sont créés par une société."
            );
        }

        String email = request.getEmail().trim().toLowerCase();
        if (utilisateurRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email déjà utilisé");
        }

        String rawPassword = request.getMotDePasse();

        // ---- Création Société ----
        if (request.getSocieteNom() == null || request.getSocieteNom().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le nom de la société est obligatoire");
        }

        Societe soc = new Societe();
        soc.setNomSoc(request.getSocieteNom());
        soc.setMailSoc(request.getSocieteEmail());

        String telSoc = (request.getSocieteTel() != null && !request.getSocieteTel().trim().isEmpty())
                ? request.getSocieteTel()
                : request.getTel();

        soc.setTelSoc(telSoc);
        soc.setMdpSoc(passwordEncoder.encode(rawPassword)); // optionnel si tu ne t'en sers pas

        soc = societeRepository.save(soc);

        // ---- Création utilisateur SOCIETE (admin) ----
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(request.getNom());
        utilisateur.setPrenom(request.getPrenom());
        utilisateur.setEmail(email);
        utilisateur.setMotDePasse(passwordEncoder.encode(rawPassword));
        utilisateur.setRole("SOCIETE");
        utilisateur.setTel(request.getTel());
        utilisateur.setSociete(soc);

        utilisateurRepository.save(utilisateur);

        String jwt = jwtUtil.generateToken(utilisateur.getEmail());

        // ✅ IMPORTANT : renvoyer AUSSI le role
        return new AuthResponse(jwt, utilisateur.getRole());
    }

    @Override
    public AuthResponse login(AuthRequest request) {

        final String email = request.getEmail().trim().toLowerCase();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, request.getMotDePasse())
            );
        } catch (org.springframework.security.authentication.BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email ou mot de passe incorrect");
        } catch (org.springframework.security.authentication.DisabledException |
                 org.springframework.security.authentication.LockedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }

        Utilisateur user = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable"));

        String jwt = jwtUtil.generateToken(user.getEmail());

        // ✅ IMPORTANT : renvoyer AUSSI le role
        return new AuthResponse(jwt, user.getRole());
    }
}
