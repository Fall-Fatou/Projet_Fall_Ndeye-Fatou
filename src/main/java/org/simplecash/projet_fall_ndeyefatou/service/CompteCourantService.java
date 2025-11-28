package org.simplecash.projet_fall_ndeyefatou.service;

import org.simplecash.projet_fall_ndeyefatou.dto.CompteCourantDto;

public interface CompteCourantService {
    CompteCourantDto createCompteCourant(Long clientId);
    CompteCourantDto getCompteCourantByClientId(Long clientId);
}
