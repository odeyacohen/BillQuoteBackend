package com.billquote.service;

import com.billquote.dto.DevisDTO;
import com.billquote.entity.Devis;
import com.billquote.repository.DevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DevisServiceImpl implements DevisService {

    @Autowired
    private DevisRepository devisRepository;

    private DevisDTO mapToDTO(Devis devis) {
        DevisDTO dto = new DevisDTO();
        dto.setIdDevis(devis.getIdDevis());
        dto.setTitre(devis.getTitre());
        dto.setMontant(devis.getMontant());
        dto.setDateCreation(devis.getDateCreation());
        dto.setIdUtilisateur(devis.getIdUtilisateur());
        dto.setIdSociete(devis.getIdSociete());
        return dto;
    }

    private Devis mapToEntity(DevisDTO dto) {
        Devis devis = new Devis();
        devis.setIdDevis(dto.getIdDevis());
        devis.setTitre(dto.getTitre());
        devis.setMontant(dto.getMontant());
        devis.setDateCreation(dto.getDateCreation());
        devis.setIdUtilisateur(dto.getIdUtilisateur());
        devis.setIdSociete(dto.getIdSociete());
        return devis;
    }

    @Override
    public DevisDTO createDevis(DevisDTO devisDTO) {
        return mapToDTO(devisRepository.save(mapToEntity(devisDTO)));
    }

    @Override
    public List<DevisDTO> getAllDevis() {
        return devisRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public DevisDTO getDevisById(String id) {
        return mapToDTO(devisRepository.findById(id).orElseThrow(() -> new RuntimeException("Devis non trouvé")));
    }

    @Override
    public void deleteDevis(String id) {
        devisRepository.deleteById(id);
    }
}
