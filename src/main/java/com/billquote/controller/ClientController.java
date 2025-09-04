package com.billquote.controller;

import com.billquote.dto.ClientDTO;
import com.billquote.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService; // injection par constructeur

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO clientDTO,
                                                  UriComponentsBuilder uriBuilder) {
        ClientDTO created = clientService.createClient(clientDTO);
        // /api/clients/{id}
        var location = uriBuilder.path("/api/clients/{id}")
                                 .buildAndExpand(created.getId())
                                 .toUri();
        return ResponseEntity.created(location).body(created); // 201 + Location
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id,
                                                  @Valid @RequestBody ClientDTO updatedClient) {
        return ResponseEntity.ok(clientService.updateClient(id, updatedClient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
