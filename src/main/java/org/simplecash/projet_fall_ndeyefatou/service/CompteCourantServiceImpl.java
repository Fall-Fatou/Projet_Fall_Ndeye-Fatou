package org.simplecash.projet_fall_ndeyefatou.service;

import lombok.RequiredArgsConstructor;
import org.simplecash.projet_fall_ndeyefatou.dto.CompteCourantDto;
import org.simplecash.projet_fall_ndeyefatou.entity.ClientEntity;
import org.simplecash.projet_fall_ndeyefatou.entity.CompteCourantEntity;
import org.simplecash.projet_fall_ndeyefatou.mapper.CompteCourantMapper;
import org.simplecash.projet_fall_ndeyefatou.repository.ClientRepository;
import org.simplecash.projet_fall_ndeyefatou.repository.CompteCourantRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompteCourantServiceImpl implements CompteCourantService {

    private final ClientRepository clientRepository;
    private final CompteCourantRepository compteCourantRepository;
    private final CompteCourantMapper mapper;

    @Override
    public CompteCourantDto createCompteCourant(Long clientId) {
        ClientEntity client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        CompteCourantEntity compte = new CompteCourantEntity(client);
        CompteCourantEntity saved = compteCourantRepository.save(compte);
        return mapper.toDto(saved);
    }

    @Override
    public CompteCourantDto getCompteCourantByClientId(Long clientId) {
        CompteCourantEntity compte = compteCourantRepository.findByClientId(clientId)
                .orElseThrow(() -> new RuntimeException("Compte courant not found"));
        return mapper.toDto(compte);
    }
}
