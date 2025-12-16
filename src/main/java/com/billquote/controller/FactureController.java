package com.billquote.controller;

import com.billquote.dto.FactureDTO;
import com.billquote.entity.Facture;
import com.billquote.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factures")
public class FactureController {

    private final FactureService factureService;

    @Autowired
    public FactureController(FactureService factureService) {
        this.factureService = factureService;
    }

    @PostMapping
    public ResponseEntity<FactureDTO> createFacture(@RequestBody FactureDTO factureDTO) {
        FactureDTO created = factureService.createFacture(factureDTO);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<FactureDTO>> getAllFactures() {
        List<FactureDTO> factures = factureService.getAllFactures();
        return ResponseEntity.ok(factures);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactureDTO> getFactureById(@PathVariable Long id) {
        FactureDTO facture = factureService.getFactureById(id);
        return ResponseEntity.ok(facture);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacture(@PathVariable Long id) {
        factureService.deleteFacture(id);
        return ResponseEntity.noContent().build();
    }

    // ===================== PDF FACTURE =====================

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> pdfFacture(@PathVariable Long id) {
        byte[] pdf = factureService.generatePdf(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        // "attachment" = téléchargement direct ; "inline" = affichage dans le navigateur
        headers.setContentDispositionFormData(
                "attachment",
                "facture-" + id + ".pdf"
        );

        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }
    
 

    // ... POST, GET, DELETE, /pdf que tu as déjà

    // ===================== UPDATE STATUT FACTURE =====================

    @PatchMapping("/{id}/statut")
    public ResponseEntity<FactureDTO> updateFactureStatus(
            @PathVariable Long id,
            @RequestBody FactureDTO dto
    ) {
        Facture.StatutFacture newStatus = dto.getStatutFacture();
        FactureDTO updated = factureService.updateFactureStatus(id, newStatus);
        return ResponseEntity.ok(updated);
    }
}
