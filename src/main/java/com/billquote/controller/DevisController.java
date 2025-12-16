package com.billquote.controller;

import com.billquote.dto.DevisDTO;
import com.billquote.service.DevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devis")
public class DevisController {

    @Autowired
    private DevisService devisService;

    @PostMapping
    public ResponseEntity<DevisDTO> createDevis(@RequestBody DevisDTO devisDTO) {
        return ResponseEntity.ok(devisService.createDevis(devisDTO));
    }

    @GetMapping
    public ResponseEntity<List<DevisDTO>> getAllDevis() {
        return ResponseEntity.ok(devisService.getAllDevis());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DevisDTO> getDevisById(@PathVariable Long id) {
        return ResponseEntity.ok(devisService.getDevisById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevis(@PathVariable Long id) {
        devisService.deleteDevis(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> pdfDevis(@PathVariable Long id) {
        byte[] pdf = devisService.generatePdf(id);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=devis-" + id + ".pdf")
                .header("Content-Type", "application/pdf")
                .body(pdf);
    }
}
