package org.simplecash.projet_fall_ndeyefatou.service;

import lombok.RequiredArgsConstructor;
import org.simplecash.projet_fall_ndeyefatou.dto.ClientCreateDto;
import org.simplecash.projet_fall_ndeyefatou.dto.ClientDto;
import org.simplecash.projet_fall_ndeyefatou.entity.ClientEntity;
import org.simplecash.projet_fall_ndeyefatou.mapper.ClientMapper;
import org.simplecash.projet_fall_ndeyefatou.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImplem implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toClientDto)
                .toList();
    }

    @Override
    public Optional<ClientDto> getClientById(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::toClientDto);
    }

    @Override
    public ClientDto saveClient(ClientCreateDto dto) {
        ClientEntity clientEntity = clientMapper.toClientEntity(dto);
        ClientEntity savedEntity = clientRepository.save(clientEntity);
        return clientMapper.toClientDto(savedEntity);
    }
}
