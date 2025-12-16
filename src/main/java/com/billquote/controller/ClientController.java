package com.billquote.controller;

import com.billquote.dto.ClientDTO;
import com.billquote.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // CREATE
    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        ClientDTO saved = clientService.createClient(clientDTO);
        URI location = URI.create("/api/clients/" + saved.getIdClient());
        return ResponseEntity.created(location).body(saved);
    }

    // READ - all
    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    // READ - by id
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    // READ - by name (optionnel, mais tu as déjà la méthode de service)
    @GetMapping("/by-name/{nom}")
    public ResponseEntity<ClientDTO> getClientByNom(@PathVariable String nom) {
        return ResponseEntity.ok(clientService.getClientByNom(nom));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id,
                                                  @RequestBody ClientDTO updatedClient) {
        ClientDTO dto = clientService.updateClient(id, updatedClient);
        return ResponseEntity.ok(dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
