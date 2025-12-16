package com.billquote.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.billquote.dto.SocieteDTO;
import com.billquote.entity.Societe;
import com.billquote.config.ResourceNotFoundException;
import com.billquote.repository.SocieteRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SocieteServiceImpl implements SocieteService {

  private final SocieteRepository repository;

  private SocieteDTO toDTO(Societe entity) {
    SocieteDTO dto = new SocieteDTO();
    dto.setId(entity.getId());
    dto.setNomSoc(entity.getNomSoc());
    dto.setMailSoc(entity.getMailSoc());
    dto.setTelSoc(entity.getTelSoc());
    dto.setMdpSoc(entity.getMdpSoc());
    return dto;
  }

  private Societe toEntity(SocieteDTO dto) {
    return Societe.builder()
        .id(dto.getId())
        .nomSoc(dto.getNomSoc())
        .mailSoc(dto.getMailSoc())
        .telSoc(dto.getTelSoc())
        .mdpSoc(dto.getMdpSoc())
        .build();
  }

  @Override
  public SocieteDTO create(SocieteDTO dto) {
	  Societe saved = repository.save(toEntity(dto));
    return toDTO(saved);
  }

  @Override
  @Transactional(readOnly = true)
  public SocieteDTO getById(Long id) {
	  Societe entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Société introuvable: " + id));
    return toDTO(entity);
  }

  @Override
  public SocieteDTO update(Long id, SocieteDTO dto) {
	  Societe entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Société introuvable: " + id));

    entity.setNomSoc(dto.getNomSoc());
    entity.setMailSoc(dto.getMailSoc());
    entity.setTelSoc(dto.getTelSoc());
    entity.setMdpSoc(dto.getMdpSoc());

    return toDTO(repository.save(entity));
  }

  @Transactional(readOnly = true)
  public List<SocieteDTO> getAll() {
    return repository.findAll()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
  }
}