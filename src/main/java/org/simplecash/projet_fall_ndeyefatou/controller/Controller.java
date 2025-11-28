package org.simplecash.projet_fall_ndeyefatou.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.simplecash.projet_fall_ndeyefatou.dto.ClientCreateDto;
import org.simplecash.projet_fall_ndeyefatou.dto.ClientDto;
import org.simplecash.projet_fall_ndeyefatou.dto.ConseillerCreateDto;
import org.simplecash.projet_fall_ndeyefatou.dto.ConseillerDto;
import org.simplecash.projet_fall_ndeyefatou.service.ClientService;
import org.simplecash.projet_fall_ndeyefatou.service.ConseillerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class Controller {
    private final ConseillerService conseillerService;
    private final ClientService clientService;

    @GetMapping("/conseillers")
    public List<ConseillerDto> getConseillers() {
        return conseillerService.findAll();
    }

    @PostMapping("/conseillers")
    ConseillerDto save(@RequestBody @Valid ConseillerCreateDto createDto) {
        return conseillerService.save(createDto);
    }

    @GetMapping("conseillers/{id}")
    ResponseEntity<ConseillerDto> getConseiller(@PathVariable Long id) {
        return conseillerService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/clients")
    public ResponseEntity<?> getClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @PostMapping("/clients")
    ClientDto save(@RequestBody @Valid ClientCreateDto createDto) {
        return clientService.saveClient(createDto);
    }

    @GetMapping("clients/{id}")
    ResponseEntity<ClientDto> getClient(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
