package com.billquote.controller;

import com.billquote.dto.FactureDTO;
import com.billquote.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<FactureDTO> getFactureById(@PathVariable String id) {
        FactureDTO facture = factureService.getFactureById(id);
        return ResponseEntity.ok(facture);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacture(@PathVariable String id) {
        factureService.deleteFacture(id);
        return ResponseEntity.noContent().build();
    }
}
