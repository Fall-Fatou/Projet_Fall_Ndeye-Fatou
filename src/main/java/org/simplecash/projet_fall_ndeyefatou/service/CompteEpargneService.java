package org.simplecash.projet_fall_ndeyefatou.service;

import org.simplecash.projet_fall_ndeyefatou.dto.CompteEpargneDto;

public interface CompteEpargneService {

    CompteEpargneDto createCompteEpargne(Long clientId);

    CompteEpargneDto getCompteEpargneByClientId(Long clientId);
}
