package com.abelgrajales.mirmtz.controller;

import com.abelgrajales.mirmtz.dto.MirFinDTO;
import com.abelgrajales.mirmtz.dto.request.MirFinPropositoRequest;
import com.abelgrajales.mirmtz.services.MirFinService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mirfin")
public class MirFinController {

    private final MirFinService mirFinService;

    @Autowired
    public MirFinController(MirFinService mirFinService) {
        this.mirFinService = mirFinService;
    }

    @PostMapping
    public ResponseEntity<MirFinDTO> save(@Valid @RequestBody MirFinPropositoRequest request) {
        MirFinDTO savedMirFin = mirFinService.save(request);
        return ResponseEntity.ok(savedMirFin);
    }

    @PutMapping
    public ResponseEntity<MirFinDTO> update(@Valid @RequestBody MirFinPropositoRequest request) {
        MirFinDTO updatedMirFin = mirFinService.update(request);
        return ResponseEntity.ok(updatedMirFin);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MirFinDTO> findById(@PathVariable Long id) {
        MirFinDTO mirFinDTO = mirFinService.findById(id);
        return ResponseEntity.ok(mirFinDTO);
    }

}
