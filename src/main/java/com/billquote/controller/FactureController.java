package com.billquote.controller;

import com.billquote.dto.FactureDTO;
import com.billquote.service.FactureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/factures")
@RequiredArgsConstructor
public class FactureController {

    private final FactureService factureService;

    @PostMapping
    public ResponseEntity<FactureDTO> createFacture(@Valid @RequestBody FactureDTO factureDTO,
                                                    UriComponentsBuilder uriBuilder) {
        FactureDTO created = factureService.createFacture(factureDTO);
        var location = uriBuilder.path("/api/factures/{id}")
                                 .buildAndExpand(created.getIdFacture())
                                 .toUri();
        return ResponseEntity.created(location).body(created); // 201 + Location
    }

    @GetMapping
    public ResponseEntity<List<FactureDTO>> getAllFactures() {
        return ResponseEntity.ok(factureService.getAllFactures());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactureDTO> getFactureById(@PathVariable String id) {
        return ResponseEntity.ok(factureService.getFactureById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacture(@PathVariable String id) {
        factureService.deleteFacture(id);
        return ResponseEntity.noContent().build();
    }
}
