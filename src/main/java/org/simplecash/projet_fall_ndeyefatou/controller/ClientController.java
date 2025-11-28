package org.simplecash.projet_fall_ndeyefatou.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.simplecash.projet_fall_ndeyefatou.dto.*;
import org.simplecash.projet_fall_ndeyefatou.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("clients")
@Tag(
        name = "Clients",
        description = "API de gestion des clients : création, modification, suppression et consultation."
)
public class ClientController {

    private final ClientService clientService;

    @Operation(
            summary = "Liste des clients",
            description = "Retourne la liste complète des clients enregistrés dans le système."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste retournée avec succès")
    })
    @GetMapping
    public List<ClientDto> getAll() {
        return clientService.getAllClients();
    }

    @Operation(
            summary = "Créer un client",
            description = """
                    Crée un nouveau client et l'associe automatiquement à un conseiller.
                    Un compte courant est créé automatiquement lors de cette opération.
                    """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Erreur de validation ou conseiller introuvable")
    })
    @PostMapping
    public ClientDto create(@RequestBody @Valid ClientCreateDto dto) {
        return clientService.saveClient(dto);
    }

    @Operation(
            summary = "Rechercher un client par ID",
            description = "Retourne les informations du client correspondant à l'ID fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client trouvé"),
            @ApiResponse(responseCode = "404", description = "Aucun client ne correspond à cet ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getById(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Modifier un client",
            description = "Met à jour les informations d'un client existant."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client mis à jour"),
            @ApiResponse(responseCode = "400", description = "Données invalides ou client introuvable")
    })
    @PutMapping("{id}")
    public ResponseEntity<ClientDto> update(
            @PathVariable Long id,
            @RequestBody @Valid ClientUpdateDto dto
    ) {
        return ResponseEntity.ok(clientService.updateClient(id, dto));
    }

    @Operation(
            summary = "Supprimer un client",
            description = """
                    Supprime un client uniquement si :
                    - son compte courant a un solde égal à 0€
                    - son compte épargne (s’il existe) a aussi un solde égal à 0€
                    """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Client supprimé avec succès"),
            @ApiResponse(responseCode = "400", description = "Suppression impossible (solde non nul ou client introuvable)")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            clientService.deleteClient(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
