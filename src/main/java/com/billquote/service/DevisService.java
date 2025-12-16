package com.billquote.service;

import com.billquote.dto.DevisDTO;
import java.util.List;

public interface DevisService {
    DevisDTO createDevis(DevisDTO devisDTO);
    List<DevisDTO> getAllDevis();
    byte[] generatePdf(Long id);
	DevisDTO getDevisById(Long id);
	void deleteDevis(Long id);
	  
}