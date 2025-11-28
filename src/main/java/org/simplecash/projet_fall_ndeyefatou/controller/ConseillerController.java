package org.simplecash.projet_fall_ndeyefatou.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.simplecash.projet_fall_ndeyefatou.dto.ConseillerCreateDto;
import org.simplecash.projet_fall_ndeyefatou.dto.ConseillerDto;
import org.simplecash.projet_fall_ndeyefatou.dto.ConseillerUpdateDto;
import org.simplecash.projet_fall_ndeyefatou.service.ConseillerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("conseillers")
@Tag(
        name = "Conseillers",
        description = "Gestion des conseillers bancaires : création, consultation, recherche."
)
public class ConseillerController {

    private final ConseillerService conseillerService;

    @Operation(
            summary = "Lister tous les conseillers",
            description = "Retourne la liste complète des conseillers disponibles dans le système."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste retournée avec succès")
    })
    @GetMapping
    public List<ConseillerDto> getAll() {
        return conseillerService.findAll();
    }

    @Operation(
            summary = "Créer un nouveau conseiller",
            description = "Crée un conseiller avec son nom, email et téléphone (optionnel)."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Conseiller créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Validation incorrecte des données")
    })
    @PostMapping
    public ConseillerDto create(@RequestBody @Valid ConseillerCreateDto dto) {
        return conseillerService.save(dto);
    }

    @Operation(
            summary = "Obtenir un conseiller par ID",
            description = "Retourne le conseiller correspondant à l'identifiant fourni."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Conseiller trouvé"),
            @ApiResponse(responseCode = "404", description = "Aucun conseiller ne correspond à cet ID")
    })
    @GetMapping("{id}")
    public ResponseEntity<ConseillerDto> getById(@PathVariable Long id) {
        return conseillerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PatchMapping("{id}")
    public ResponseEntity<ConseillerDto> patch(
            @PathVariable Long id,
            @RequestBody ConseillerUpdateDto dto
    ) {
        return ResponseEntity.ok(conseillerService.patch(id, dto));
    }

}
