package com.billquote.service;

import com.billquote.dto.DevisDTO;
import java.util.List;

public interface DevisService {
    DevisDTO createDevis(DevisDTO devisDTO);
    List<DevisDTO> getAllDevis();
    DevisDTO getDevisById(String id);
    void deleteDevis(String id);
}