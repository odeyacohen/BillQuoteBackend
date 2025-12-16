package com.billquote.service;

import java.util.List;

import com.billquote.dto.SocieteDTO;


public interface SocieteService {


	  SocieteDTO create(SocieteDTO dto);
	  SocieteDTO getById(Long id);
	  SocieteDTO update(Long id, SocieteDTO dto);
	List<SocieteDTO> getAll();
	  
	

}
