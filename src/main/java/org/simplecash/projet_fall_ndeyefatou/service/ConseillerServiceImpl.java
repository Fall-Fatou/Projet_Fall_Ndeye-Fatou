package org.simplecash.projet_fall_ndeyefatou.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.simplecash.projet_fall_ndeyefatou.dto.ConseillerCreateDto;
import org.simplecash.projet_fall_ndeyefatou.dto.ConseillerDto;
import org.simplecash.projet_fall_ndeyefatou.entity.ConseillerEntity;
import org.simplecash.projet_fall_ndeyefatou.mapper.ConseillerMapper;
import org.simplecash.projet_fall_ndeyefatou.repository.ConseillerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConseillerServiceImpl implements ConseillerService {
    private final ConseillerRepository conseillerRepository;
    private final ConseillerMapper conseillerMapper;

    @PostConstruct
    private void initdb() {
        conseillerRepository.saveAll(List.of(
                new ConseillerEntity("Fall Ndeye Fatou", "fallfatou331@gmail.com", "1234")
                )
        );
    }
    @Override
    public List<ConseillerDto> findAll() {
        return conseillerRepository.findAll().stream().map(conseillerMapper::toDto).toList();
    }

    @Override
    public Optional<ConseillerDto> findById(Long id) {
        return conseillerRepository.findById(id).map(conseillerMapper::toDto);
    }

    @Override
    public ConseillerDto save(ConseillerCreateDto conseillerDto) {
        ConseillerEntity entity = conseillerMapper.toEntity(conseillerDto);
        ConseillerEntity saved = conseillerRepository.save(entity);
        return conseillerMapper.toDto(saved);
    }
}
