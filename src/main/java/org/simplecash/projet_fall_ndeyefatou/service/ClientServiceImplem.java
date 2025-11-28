package org.simplecash.projet_fall_ndeyefatou.service;

import lombok.RequiredArgsConstructor;
import org.simplecash.projet_fall_ndeyefatou.dto.ClientCreateDto;
import org.simplecash.projet_fall_ndeyefatou.dto.ClientDto;
import org.simplecash.projet_fall_ndeyefatou.dto.ClientUpdateDto;
import org.simplecash.projet_fall_ndeyefatou.entity.ClientEntity;
import org.simplecash.projet_fall_ndeyefatou.mapper.ClientMapper;
import org.simplecash.projet_fall_ndeyefatou.repository.ClientRepository;
import org.simplecash.projet_fall_ndeyefatou.repository.CompteCourantRepository;
import org.simplecash.projet_fall_ndeyefatou.repository.CompteEpargneRepository;
import org.simplecash.projet_fall_ndeyefatou.repository.ConseillerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImplem implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final CompteCourantService compteCourantService;
    private final ConseillerRepository conseillerRepository;
    private final CompteCourantRepository compteCourantRepository;
    private final CompteEpargneRepository compteEpargneRepository;


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

        var conseiller = conseillerRepository.findById(dto.conseillerId())
                .orElseThrow(() -> new RuntimeException("Conseiller not found"));
        int count = clientRepository.countByConseillerId(conseiller.getId());
        if (count >= 10) {
            throw new RuntimeException("Conseiller already has 10 clients.");
        }
        ClientEntity entity = clientMapper.toClientEntity(dto);
        entity.setConseiller(conseiller);
        ClientEntity saved = clientRepository.save(entity);
        compteCourantService.createCompteCourant(saved.getId());
        return clientMapper.toClientDto(saved);
    }
    @Override
    public void deleteClient(Long id) {

        ClientEntity client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        var compteCourant = compteCourantRepository.findByClientId(id)
                .orElseThrow(() -> new RuntimeException("Client has no current account"));

        if (compteCourant.getSolde() != 0.0) {
            throw new RuntimeException("Cannot delete client: current account balance must be 0€");
        }

        var compteEpargneOpt = compteEpargneRepository.findByClientId(id);

        if (compteEpargneOpt.isPresent()) {
            var compteEpargne = compteEpargneOpt.get();

            if (compteEpargne.getSolde() != 0.0) {
                throw new RuntimeException("Cannot delete client: savings account balance must be 0€");
            }
        }

        clientRepository.delete(client);
    }

    @Override
    public ClientDto updateClient(Long id, ClientUpdateDto dto) {
        ClientEntity entity = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        clientMapper.updateEntity(entity, dto);
        ClientEntity updated = clientRepository.save(entity);

        return clientMapper.toClientDto(updated);
    }
}
