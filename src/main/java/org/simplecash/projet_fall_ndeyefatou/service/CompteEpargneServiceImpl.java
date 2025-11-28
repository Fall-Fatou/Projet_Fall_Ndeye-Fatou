package org.simplecash.projet_fall_ndeyefatou.service;

import lombok.RequiredArgsConstructor;
import org.simplecash.projet_fall_ndeyefatou.dto.CompteEpargneDto;
import org.simplecash.projet_fall_ndeyefatou.entity.ClientEntity;
import org.simplecash.projet_fall_ndeyefatou.entity.CompteEpargneEntity;
import org.simplecash.projet_fall_ndeyefatou.mapper.CompteEpargneMapper;
import org.simplecash.projet_fall_ndeyefatou.repository.ClientRepository;
import org.simplecash.projet_fall_ndeyefatou.repository.CompteEpargneRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompteEpargneServiceImpl implements CompteEpargneService {

    private final ClientRepository clientRepository;
    private final CompteEpargneRepository compteEpargneRepository;
    private final CompteEpargneMapper mapper;

    @Override
    public CompteEpargneDto createCompteEpargne(Long clientId) {

        ClientEntity client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        compteEpargneRepository.findByClientId(clientId)
                .ifPresent(c -> { throw new RuntimeException("Client already has a savings account"); });

        CompteEpargneEntity compte = new CompteEpargneEntity(client);
        CompteEpargneEntity saved = compteEpargneRepository.save(compte);

        return mapper.toDto(saved);
    }

    @Override
    public CompteEpargneDto getCompteEpargneByClientId(Long clientId) {
        CompteEpargneEntity compte = compteEpargneRepository.findByClientId(clientId)
                .orElseThrow(() -> new RuntimeException("Savings account not found"));
        return mapper.toDto(compte);
    }
}
