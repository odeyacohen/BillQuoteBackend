package com.billquote.service;

import com.billquote.dto.FactureDTO;
import com.billquote.entity.Client;
import com.billquote.entity.Facture;
import com.billquote.entity.Facture.StatutFacture;
import com.billquote.model.PdfFile;
import com.billquote.repository.ClientRepository;
import com.billquote.repository.FactureRepository;
import com.billquote.security.AppUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class FactureServiceImpl implements FactureService {

    private final FactureRepository factureRepository;
    private final ClientRepository clientRepository;
    private final PdfService pdfService;          // (encore utile ailleurs si tu veux)
    private final PdfStorageService pdfStorageService; // 👈 ajouté

    /* ===================== CRUD ===================== */

    @Override
    @Transactional
    public FactureDTO createFacture(FactureDTO dto) {
        Facture entity = toEntity(dto);

        // ✅ on génère TOUJOURS un numéro unique, on ignore ce qui vient du front
        entity.setNumeroFacture(generateNumeroFacture());

        if (entity.getDateFacture() == null) {
            entity.setDateFacture(Instant.now());
        }
        if (entity.getCreatedAt() == null) {
            entity.setCreatedAt(Instant.now());
        }
        entity.setUpdatedAt(Instant.now());

        if (entity.getMontant() == null) {
            entity.setMontant(BigDecimal.ZERO);
        }

        Facture saved = factureRepository.save(entity);
        return toDTO(saved);
    }

    @Override
    public List<FactureDTO> getAllFactures() {
        return factureRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FactureDTO getFactureById(Long id) {
        Facture f = factureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facture non trouvée: " + id));
        return toDTO(f);
    }

    @Override
    @Transactional
    public void deleteFacture(Long id) {
        if (!factureRepository.existsById(id)) {
            throw new RuntimeException("Facture non trouvée: " + id);
        }
        factureRepository.deleteById(id);
    }

    /* ===================== PDF ===================== */

    @Override
    @Transactional
    public byte[] generatePdf(Long id) {
        Facture f = factureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facture introuvable: " + id));

        // 1) Si le PDF existe déjà en base, on le renvoie
        if (f.getPdfFile() != null && f.getPdfFile().getData() != null) {
            return f.getPdfFile().getData();
        }

        // 2) Sinon, on prépare les strings pour PdfTemplates.facture(...)
        // Formatage du montant en € (FR)
        String montantStr = "";
        if (f.getMontant() != null) {
            NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.FRANCE);
            montantStr = nf.format(f.getMontant());
        }

        // Formatage de la date (Instant -> yyyy-MM-dd)
        String dateIso = "";
        if (f.getDateFacture() != null) {
            DateTimeFormatter fmt = DateTimeFormatter.ISO_LOCAL_DATE;
            dateIso = fmt.format(
                    f.getDateFacture()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
            );
        }

        String statut = f.getStatutFacture() != null ? f.getStatutFacture().name() : "";

        // 3) On génère et stocke le PDF via PdfStorageService
        PdfFile pdfFile = pdfStorageService.createFacturePdf(
                f.getNumeroFacture(),
                montantStr,
                dateIso,
                statut
        );

        // 4) On l’attache à la facture et on sauvegarde
        f.setPdfFile(pdfFile);
        factureRepository.save(f);

        // 5) On renvoie les bytes
        return pdfFile.getData();
    }

    /* ===================== HELPERS ===================== */

    /** Génère un numéro simple du type FAC-0001, FAC-0002, etc. */
    private String generateNumeroFacture() {
        long count = factureRepository.count() + 1;
        return "FAC-" + String.format("%04d", count);
    }

    private FactureDTO toDTO(Facture f) {
        FactureDTO dto = new FactureDTO();
        dto.setIdFacture(f.getIdFacture());
        dto.setNumeroFacture(f.getNumeroFacture());
        dto.setMontantFacture(f.getMontant());
        dto.setDateFacture(f.getDateFacture());
        dto.setStatutFacture(f.getStatutFacture());
        dto.setIdClient(f.getIdClient());
        dto.setIdSociete(f.getIdSociete());
        dto.setIdUtilisateur(f.getIdUtilisateur());
        dto.setCreatedAt(f.getCreatedAt());
        dto.setUpdatedAt(f.getUpdatedAt());

        // 🔗 remonter le nom du client
        if (f.getIdClient() != null) {
            clientRepository.findById(f.getIdClient())
                    .ifPresent(client -> dto.setNomClient(client.getNomClient()));
        }

        return dto;
    }

    private Facture toEntity(FactureDTO dto) {
        Facture f = new Facture();
        f.setIdFacture(dto.getIdFacture());
        // on NE reprend PAS le numeroFacture du DTO → il sera généré
        f.setMontant(dto.getMontantFacture());
        f.setDateFacture(dto.getDateFacture()); // si null, corrigé dans createFacture
        f.setStatutFacture(dto.getStatutFacture());

        // ✅ idSociete : 0 par défaut si non fourni
        if (dto.getIdSociete() == null) {
            f.setIdSociete(0L);
        } else {
            f.setIdSociete(dto.getIdSociete());
        }

        // ✅ idUtilisateur : TOUJOURS pris depuis l'utilisateur connecté
        Long currentUserId = getCurrentUserId();
        if (currentUserId == null) {
            currentUserId = 0L; // fallback si jamais pas authentifié
        }
        f.setIdUtilisateur(currentUserId);

        f.setCreatedAt(dto.getCreatedAt());
        f.setUpdatedAt(dto.getUpdatedAt());

        // ---------- LOGIQUE CLIENT ----------
        Long idClient = dto.getIdClient();
        if (idClient == null || idClient == 0) {
            String nomClient = dto.getNomClient();
            if (nomClient != null && !nomClient.isBlank()) {
                Client client = clientRepository.findByNomClient(nomClient)
                        .orElseThrow(() ->
                                new RuntimeException("Client introuvable : " + nomClient));
                idClient = client.getIdClient();
            } else {
                throw new RuntimeException("Ni idClient ni nomClient n'ont été fournis");
            }
        }

        f.setIdClient(idClient);
        return f;
    }

    /** Récupère l'id de l'utilisateur connecté à partir du JWT */
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

    @Override
    @Transactional
    public FactureDTO updateFactureStatus(Long id, StatutFacture newStatus) {
        Facture f = factureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facture non trouvée: " + id));

        f.setStatutFacture(newStatus);
        f.setUpdatedAt(Instant.now());

        Facture saved = factureRepository.save(f);
        return toDTO(saved);
    }
}
