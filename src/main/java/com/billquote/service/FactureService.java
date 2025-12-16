package com.billquote.service;

import com.billquote.dto.FactureDTO;
import com.billquote.entity.Facture;

import java.util.List;

public interface FactureService {
    FactureDTO createFacture(FactureDTO factureDTO);
    List<FactureDTO> getAllFactures();
    FactureDTO getFactureById(Long id);
    void deleteFacture(Long id);
    byte[] generatePdf(Long  id);  
    FactureDTO updateFactureStatus(Long id, Facture.StatutFacture newStatus);
}
