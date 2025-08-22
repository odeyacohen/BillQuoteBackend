package com.billquote.service;

import com.billquote.dto.AuthRequest;
import com.billquote.dto.AuthResponse;
import com.billquote.dto.RegisterRequest;
import com.billquote.entity.Utilisateur;
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

    @Override
    public AuthResponse register(RegisterRequest request) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(request.getNom());
        utilisateur.setPrenom(request.getPrenom());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        utilisateur.setRole(request.getRole());

        utilisateurRepository.save(utilisateur);

        String jwt = jwtUtil.generateToken(utilisateur.getEmail());

        return new AuthResponse(jwt);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getMotDePasse()
            )
        );

        Utilisateur utilisateur = utilisateurRepository
            .findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        String jwt = jwtUtil.generateToken(utilisateur.getEmail());

        return new AuthResponse(jwt);
    }
}
