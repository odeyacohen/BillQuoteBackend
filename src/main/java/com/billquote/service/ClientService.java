package com.billquote.service;

import com.billquote.dto.ClientDTO;

import java.util.List;

public interface ClientService {
    ClientDTO createClient(ClientDTO clientDTO);
    ClientDTO getClientById(Long id);
    List<ClientDTO> getAllClients();
    ClientDTO updateClient(Long id, ClientDTO updatedClient);
    void deleteClient(Long id);
}
