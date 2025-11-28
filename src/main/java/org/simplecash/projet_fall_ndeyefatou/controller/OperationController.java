package org.simplecash.projet_fall_ndeyefatou.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.*;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.simplecash.projet_fall_ndeyefatou.dto.OperationDto;
import org.simplecash.projet_fall_ndeyefatou.service.OperationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("operations")
@Tag(
        name = "Opérations Bancaires",
        description = "API permettant d'effectuer des dépôts, transferts et virements entre comptes."
)
public class OperationController {

    private final OperationService operationService;

    @Operation(
            summary = "Effectuer un dépôt",
            description = "Ajoute un montant positif au solde d’un compte courant existant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Dépôt effectué avec succès"),
            @ApiResponse(responseCode = "400", description = "Montant négatif ou compte introuvable")
    })
    @PostMapping("/depot/{compteId}")
    @ResponseStatus(HttpStatus.CREATED)
    public OperationDto depot(
            @PathVariable Long compteId,
            @RequestParam @Positive Double montant
    ) {
        return operationService.depot(compteId, montant);
    }

    @Operation(
            summary = "Transférer du compte courant vers le compte épargne",
            description = "Débite le compte courant puis crédite le compte épargne, si le solde est suffisant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Transfert effectué"),
            @ApiResponse(responseCode = "400", description = "Solde insuffisant ou comptes invalides")
    })
    @PostMapping("/cc-to-ce")
    @ResponseStatus(HttpStatus.CREATED)
    public OperationDto transfertCCversCE(
            @RequestParam Long compteCourantId,
            @RequestParam Long compteEpargneId,
            @RequestParam @Positive Double montant
    ) {
        return operationService.transfertCCversCE(compteCourantId, compteEpargneId, montant);
    }

    @Operation(
            summary = "Transférer du compte épargne vers le compte courant",
            description = "Débite le compte épargne puis crédite le compte courant, uniquement si l'épargne est suffisante."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Transfert effectué"),
            @ApiResponse(responseCode = "400", description = "Solde insuffisant ou comptes invalides")
    })
    @PostMapping("/ce-to-cc")
    @ResponseStatus(HttpStatus.CREATED)
    public OperationDto transfertCEversCC(
            @RequestParam Long compteEpargneId,
            @RequestParam Long compteCourantId,
            @RequestParam @Positive Double montant
    ) {
        return operationService.transfertCEversCC(compteEpargneId, compteCourantId, montant);
    }

    @Operation(
            summary = "Effectuer un virement entre deux comptes courants",
            description = "Permet un virement entre deux comptes courants (de deux clients différents), en respectant le découvert autorisé."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Virement réalisé"),
            @ApiResponse(responseCode = "400", description = "Solde insuffisant, comptes identiques ou compte introuvable")
    })
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
