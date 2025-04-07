package com.abelgrajales.mirmtz.controller;

import com.abelgrajales.mirmtz.dto.EstrategiaDTO;
import com.abelgrajales.mirmtz.services.EstrategiaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estrategia")
public class EstrategiaController {

    private final EstrategiaService estrategiaService;

    @Autowired
    public EstrategiaController(EstrategiaService estrategiaService) {
        this.estrategiaService = estrategiaService;
    }

    @PostMapping
    public ResponseEntity<EstrategiaDTO> save(@Valid @RequestBody EstrategiaDTO estrategiaDTO) {
        EstrategiaDTO savedEstrategia = estrategiaService.save(estrategiaDTO);
        return ResponseEntity.ok(savedEstrategia);
    }

    @PutMapping
    public ResponseEntity<EstrategiaDTO> update(@Valid @RequestBody EstrategiaDTO estrategiaDTO) {
        EstrategiaDTO updatedEstrategia = estrategiaService.update(estrategiaDTO);
        return ResponseEntity.ok(updatedEstrategia);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstrategiaDTO> findById(@PathVariable Long id) {
        EstrategiaDTO estrategiaDTO = estrategiaService.findById(id);
        return ResponseEntity.ok(estrategiaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        estrategiaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EstrategiaDTO>> findAll() {
        List<EstrategiaDTO> estrategias = estrategiaService.findAll();
        return ResponseEntity.ok(estrategias);
    }
}
