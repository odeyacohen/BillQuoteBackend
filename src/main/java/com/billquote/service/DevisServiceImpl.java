package com.billquote.service;

import com.billquote.dto.DevisDTO;
import com.billquote.entity.Devis;
import com.billquote.entity.Client;
import com.billquote.model.PdfFile;
import com.billquote.repository.DevisRepository;
import com.billquote.repository.ClientRepository;
import com.billquote.security.AppUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DevisServiceImpl implements DevisService {

    @Autowired
    private DevisRepository devisRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PdfStorageService pdfStorageService;

    // --- Mapping ENTITY -> DTO ---
    private DevisDTO mapToDTO(Devis devis) {
        DevisDTO dto = new DevisDTO();
        dto.setIdDevis(devis.getIdDevis());
        dto.setTitre(devis.getTitre());
        dto.setMontant(devis.getMontant());
        dto.setDateCreation(devis.getDateCreation());
        dto.setIdUtilisateur(devis.getIdUtilisateur());
        dto.setIdSociete(devis.getIdSociete());
        dto.setIdClient(devis.getIdClient());

        // on remonte aussi le nom du client pour le front
        if (devis.getIdClient() != null) {
            clientRepository.findById(devis.getIdClient())
                    .ifPresent(client -> dto.setNomClient(client.getNomClient()));
        }

        return dto;
    }

    // --- Mapping DTO -> ENTITY ---
    private Devis mapToEntity(DevisDTO dto) {
        Devis devis = new Devis();
        devis.setIdDevis(dto.getIdDevis());
        devis.setTitre(dto.getTitre());
        devis.setMontant(dto.getMontant());
        devis.setDateCreation(dto.getDateCreation());

        // ✅ idSociete : 0L par défaut si non fourni
        Long idSociete = dto.getIdSociete();
        if (idSociete == null) {
            idSociete = 0L;
        }
        devis.setIdSociete(idSociete);

        // ✅ idUtilisateur : TOUJOURS pris depuis l'utilisateur connecté
        Long currentUserId = getCurrentUserId();
        if (currentUserId == null) {
            currentUserId = 0L; // ou lève une exception si tu veux forcer l'auth
        }
        devis.setIdUtilisateur(currentUserId);

        // si idClient déjà présent, on l’utilise
        if (dto.getIdClient() != null) {
            devis.setIdClient(dto.getIdClient());
        }
        // sinon on cherche par nomClient
        else if (dto.getNomClient() != null && !dto.getNomClient().isBlank()) {
            Client client = clientRepository.findByNomClient(dto.getNomClient())
                    .orElseThrow(() ->
                            new RuntimeException("Client introuvable avec le nom : " + dto.getNomClient()));
            devis.setIdClient(client.getIdClient());
        } else {
            throw new RuntimeException("Aucun client fourni (ni idClient ni nomClient)");
        }

        return devis;
    }

    @Override
    public byte[] generatePdf(Long id) {
        Devis devis = devisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Devis introuvable: " + id));

        // 1) Si le PDF existe déjà en base, on le renvoie
        if (devis.getPdfFile() != null && devis.getPdfFile().getData() != null) {
            return devis.getPdfFile().getData();
        }

        // 2) Sinon, on génère le PDF via PdfStorageService
        String montantStr = devis.getMontant() != null ? devis.getMontant().toPlainString() : "";
        String dateStr = devis.getDateCreation() != null ? devis.getDateCreation().toString() : "";

        PdfFile pdfFile = pdfStorageService.createDevisPdf(
                devis.getIdDevis(),
                devis.getTitre(),
                montantStr,
                dateStr
        );

        // 3) On l’attache au devis et on sauvegarde
        devis.setPdfFile(pdfFile);
        devisRepository.save(devis);

        // 4) On renvoie les bytes au contrôleur
        return pdfFile.getData();
    }

    @Override
    public DevisDTO createDevis(DevisDTO devisDTO) {
        Devis devis = mapToEntity(devisDTO);

        if (devis.getDateCreation() == null) {
            devis.setDateCreation(Instant.now());
        }

        if (devis.getMontant() == null) {
            devis.setMontant(BigDecimal.ZERO);
        }

        Devis saved = devisRepository.save(devis);
        return mapToDTO(saved);
    }

    @Override
    public List<DevisDTO> getAllDevis() {
        return devisRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DevisDTO getDevisById(Long id) {
        Devis devis = devisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Devis non trouvé"));
        return mapToDTO(devis);
    }

    @Override
    public void deleteDevis(Long id) {
        devisRepository.deleteById(id);
    }

    /** 🔐 Récupère l'id de l'utilisateur connecté à partir du JWT */
    private Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return null;
        }
        Object principal = auth.getPrincipal();
        if (principal instanceof AppUserDetails userDetails) {
            return userDetails.getIdUtilisateur();
        }
        return null;
    }
}
