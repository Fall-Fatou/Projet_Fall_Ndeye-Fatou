package org.simplecash.projet_fall_ndeyefatou.service;

import org.simplecash.projet_fall_ndeyefatou.dto.ConseillerCreateDto;
import org.simplecash.projet_fall_ndeyefatou.dto.ConseillerDto;
import org.simplecash.projet_fall_ndeyefatou.entity.ConseillerEntity;

import java.util.List;
import java.util.Optional;

public interface ConseillerService {
    List<ConseillerDto> findAll();
    Optional<ConseillerDto> findById(Long id);
    ConseillerDto save(ConseillerCreateDto conseillerDto);
}
