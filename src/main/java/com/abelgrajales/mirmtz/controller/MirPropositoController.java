package com.abelgrajales.mirmtz.controller;

import com.abelgrajales.mirmtz.dto.MirPropositoDTO;
import com.abelgrajales.mirmtz.dto.request.MirFinPropositoRequest;
import com.abelgrajales.mirmtz.services.MirPropositoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mirproposito")
public class MirPropositoController {

    private final MirPropositoService mirPropositoService;

    @Autowired
    public MirPropositoController(MirPropositoService mirPropositoService) {
        this.mirPropositoService = mirPropositoService;
    }

    @PostMapping
    public ResponseEntity<MirPropositoDTO> save(@Valid @RequestBody MirFinPropositoRequest request) {
        MirPropositoDTO savedMirProposito = mirPropositoService.save(request);
        return ResponseEntity.ok(savedMirProposito);
    }

    @PutMapping
    public ResponseEntity<MirPropositoDTO> update(@Valid @RequestBody MirFinPropositoRequest request) {
        MirPropositoDTO updatedMirProposito = mirPropositoService.update(request);
        return ResponseEntity.ok(updatedMirProposito);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MirPropositoDTO> findById(@PathVariable Long id) {
        MirPropositoDTO mirPropositoDTO = mirPropositoService.findById(id);
        return ResponseEntity.ok(mirPropositoDTO);
    }
}
