package com.billquote.controller;

import com.billquote.dto.SocieteDTO;
import com.billquote.service.SocieteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/societes")
@RequiredArgsConstructor
public class SocieteController {

  private final SocieteService service;

  @PostMapping
  public ResponseEntity<SocieteDTO> create(@Valid @RequestBody SocieteDTO dto) {
    return ResponseEntity.status(201).body(service.create(dto));
  }

  @GetMapping("/{id}")
  public SocieteDTO get(@PathVariable Long id) {
    return service.getById(id);
  }

  @PutMapping("/{id}")
  public SocieteDTO update(@PathVariable Long id, @Valid @RequestBody SocieteDTO dto) {
    return service.update(id, dto);
  }
  
  @GetMapping
  public List<SocieteDTO> getAll() {
    return service.getAll();
  }
}
