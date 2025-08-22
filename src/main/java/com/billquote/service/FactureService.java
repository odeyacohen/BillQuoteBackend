package com.billquote.service;

import com.billquote.dto.FactureDTO;
import java.util.List;

public interface FactureService {
    FactureDTO createFacture(FactureDTO factureDTO);
    List<FactureDTO> getAllFactures();
    FactureDTO getFactureById(String id);
    void deleteFacture(String id);
}
