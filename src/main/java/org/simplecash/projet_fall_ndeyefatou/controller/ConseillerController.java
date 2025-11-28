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
@RequiredArgsConstructor
@RequestMapping("conseillers")
public class ConseillerController {

    private final ConseillerService conseillerService;

    @GetMapping
    public List<ConseillerDto> getAll() {
        return conseillerService.findAll();
    }

    @PostMapping
    public ConseillerDto create(@RequestBody @Valid ConseillerCreateDto dto) {
        return conseillerService.save(dto);
    }

    @GetMapping("{id}")
    public ResponseEntity<ConseillerDto> getById(@PathVariable Long id) {
        return conseillerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
