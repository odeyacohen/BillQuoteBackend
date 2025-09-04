package com.billquote.service;

import com.billquote.dto.FactureDTO;
import com.billquote.model.DevisResponse;
import com.billquote.repository.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FactureServiceImpl implements FactureService {

    @Autowired
    private FactureRepository factureRepository;

    private FactureDTO mapToDTO(DevisResponse facture) {
        FactureDTO dto = new FactureDTO();
        dto.setIdFacture(facture.getIdFacture());
        dto.setNumeroFacture(facture.getNumeroFacture());
        dto.setDateFacture(facture.getDateFacture());
        dto.setMontantFacture(facture.getMontantFacture());
        dto.setStatutFacture(facture.getStatutFacture());
        return dto;
    }

    private DevisResponse mapToEntity(FactureDTO dto) {
        DevisResponse facture = new DevisResponse();
        facture.setIdFacture(dto.getIdFacture());
        facture.setNumeroFacture(dto.getNumeroFacture());
        facture.setDateFacture(dto.getDateFacture());
        facture.setMontantFacture(dto.getMontantFacture());
        facture.setStatutFacture(dto.getStatutFacture());
        return facture;
    }

    @Override
    public FactureDTO createFacture(FactureDTO factureDTO) {
        return mapToDTO(factureRepository.save(mapToEntity(factureDTO)));
    }

    @Override
    public List<FactureDTO> getAllFactures() {
        return factureRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public FactureDTO getFactureById(String id) {
        return mapToDTO(factureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facture non trouvée")));
    }

    @Override
    public void deleteFacture(String id) {
        factureRepository.deleteById(id);
    }
}
