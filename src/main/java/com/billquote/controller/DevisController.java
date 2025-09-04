package com.billquote.controller;

import com.billquote.dto.DevisDTO;
import com.billquote.service.DevisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/devis")
@RequiredArgsConstructor
public class DevisController {

    private final DevisService devisService; // injection par constructeur

    @PostMapping
    public ResponseEntity<DevisDTO> createDevis(@Valid @RequestBody DevisDTO devisDTO,
                                                UriComponentsBuilder uriBuilder) {
        DevisDTO created = devisService.createDevis(devisDTO);
        var location = uriBuilder.path("/api/devis/{id}")
                                 .buildAndExpand(created.getIdDevis())
                                 .toUri();
        return ResponseEntity.created(location).body(created); // 201 + Location
    }

    @GetMapping
    public ResponseEntity<List<DevisDTO>> getAllDevis() {
        return ResponseEntity.ok(devisService.getAllDevis());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DevisDTO> getDevisById(@PathVariable String id) {
        return ResponseEntity.ok(devisService.getDevisById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevis(@PathVariable String id) {
        devisService.deleteDevis(id);
        return ResponseEntity.noContent().build();
    }
}
