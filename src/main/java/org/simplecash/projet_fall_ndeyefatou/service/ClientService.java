package org.simplecash.projet_fall_ndeyefatou.service;

import org.simplecash.projet_fall_ndeyefatou.dto.ClientCreateDto;
import org.simplecash.projet_fall_ndeyefatou.dto.ClientDto;
import org.simplecash.projet_fall_ndeyefatou.dto.ClientUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<ClientDto> getAllClients();
    Optional<ClientDto> getClientById(Long id);
    ClientDto saveClient(ClientCreateDto dto);
    ClientDto updateClient(Long id, ClientUpdateDto dto);
    void deleteClient(Long id);
}
