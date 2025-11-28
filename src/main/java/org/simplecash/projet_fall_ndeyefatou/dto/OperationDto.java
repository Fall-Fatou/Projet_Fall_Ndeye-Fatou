package org.simplecash.projet_fall_ndeyefatou.dto;

import org.simplecash.projet_fall_ndeyefatou.enums.OperationType;

import java.time.LocalDateTime;

public record OperationDto(
        Long id,
        Double montant,
        LocalDateTime date,
        OperationType type,
        Long compteSourceId,
        Long compteDestinationId,
        Long epargneSourceId,
        Long epargneDestinationId
) {}

