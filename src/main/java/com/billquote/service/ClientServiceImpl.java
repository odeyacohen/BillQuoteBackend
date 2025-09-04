package com.billquote.service;

import com.billquote.dto.ClientDTO;
import com.billquote.entity.Client;
import com.billquote.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository = null;

    private ClientDTO mapToDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setIdClient(client.getIdClient());
        dto.setNomClient(client.getNomClient());
        dto.setPrenomClient(client.getPrenomClient());
        dto.setMailClient(client.getMailClient());
        dto.setTelClient(client.getTelClient());
        return dto;
    }

    private Client mapToEntity(ClientDTO dto) {
        Client client = new Client();
        client.setIdClient(dto.getIdClient());
        client.setNomClient(dto.getNomClient());
        client.setPrenomClient(dto.getPrenomClient());
        client.setMailClient(dto.getMailClient());
        client.setTelClient(dto.getTelClient());
        return client;
    }

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client saved = clientRepository.save(mapToEntity(clientDTO));
        return mapToDTO(saved);
    }

    @Override
    public ClientDTO getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return mapToDTO(client);
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO updatedClient) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        client.setNomClient(updatedClient.getNomClient());
        client.setPrenomClient(updatedClient.getPrenomClient());
        client.setMailClient(updatedClient.getMailClient());
        client.setTelClient(updatedClient.getTelClient());

        return mapToDTO(clientRepository.save(client));
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
