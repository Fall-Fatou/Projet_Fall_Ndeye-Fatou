package org.simplecash.projet_fall_ndeyefatou.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.simplecash.projet_fall_ndeyefatou.dto.OperationDto;
import org.simplecash.projet_fall_ndeyefatou.service.OperationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("operations")
public class OperationController {

    private final OperationService operationService;

    @PostMapping("depot/{compteId}")
    @ResponseStatus(HttpStatus.CREATED)
    public OperationDto depot(
            @PathVariable Long compteId,
            @RequestParam @Positive Double montant
    ) {
        return operationService.depot(compteId, montant);
    }

    @PostMapping("/cc-to-ce")
    @ResponseStatus(HttpStatus.CREATED)
    public OperationDto transfertCCversCE(
            @RequestParam Long compteCourantId,
            @RequestParam Long compteEpargneId,
            @RequestParam @Positive Double montant
    ) {
        return operationService.transfertCCversCE(compteCourantId, compteEpargneId, montant);
    }

    @PostMapping("/ce-to-cc")
    @ResponseStatus(HttpStatus.CREATED)
    public OperationDto transfertCEversCC(
            @RequestParam Long compteEpargneId,
            @RequestParam Long compteCourantId,
            @RequestParam @Positive Double montant
    ) {
        return operationService.transfertCEversCC(compteEpargneId, compteCourantId, montant);
    }

    @PostMapping("/virement")
    @ResponseStatus(HttpStatus.CREATED)
    public OperationDto virement(
            @RequestParam Long sourceCompteId,
            @RequestParam Long destinationCompteId,
            @RequestParam @Positive Double montant
    ) {
        return operationService.virement(sourceCompteId, destinationCompteId, montant);
    }
}
