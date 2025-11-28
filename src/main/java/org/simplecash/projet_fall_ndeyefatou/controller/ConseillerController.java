package org.simplecash.projet_fall_ndeyefatou.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.simplecash.projet_fall_ndeyefatou.dto.ConseillerCreateDto;
import org.simplecash.projet_fall_ndeyefatou.dto.ConseillerDto;
import org.simplecash.projet_fall_ndeyefatou.service.ConseillerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("conseillers")
@RequiredArgsConstructor
public class ConseillerController {
    private final ConseillerService conseillerService;

    @GetMapping
    public List<ConseillerDto> getConseillers() {
        return conseillerService.findAll();
    }

    @PostMapping
    ConseillerDto save(@RequestBody @Valid ConseillerCreateDto createDto) {
        return conseillerService.save(createDto);
    }

    @GetMapping("{id}")
    ResponseEntity<ConseillerDto> getConseiller(@PathVariable Long id) {
        return conseillerService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
