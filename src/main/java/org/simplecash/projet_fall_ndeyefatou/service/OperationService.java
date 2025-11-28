package org.simplecash.projet_fall_ndeyefatou.service;

import org.simplecash.projet_fall_ndeyefatou.dto.OperationDto;

import java.util.List;

public interface OperationService {

    OperationDto depot(Long compteCourantId, Double montant);
    OperationDto transfertCCversCE(Long compteCourantId, Long compteEpargneId, Double montant);
    OperationDto transfertCEversCC(Long compteEpargneId, Long compteCourantId, Double montant);
    OperationDto virement(Long sourceCompteId, Long destinationCompteId, Double montant);
    List<OperationDto> historiqueCompteCourant(Long compteCourantId);
    List<OperationDto> historiqueCompteEpargne(Long compteEpargneId);
}

