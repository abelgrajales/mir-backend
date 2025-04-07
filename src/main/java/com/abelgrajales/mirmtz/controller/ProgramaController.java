package com.abelgrajales.mirmtz.controller;

import com.abelgrajales.mirmtz.dto.ProgramaDTO;
import com.abelgrajales.mirmtz.dto.request.ProgramaRequest;
import com.abelgrajales.mirmtz.services.ProgramaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programa")
public class ProgramaController {

    private final ProgramaService programaService;

    @Autowired
    public ProgramaController(ProgramaService programaService) {
        this.programaService = programaService;
    }

    @PostMapping
    public ResponseEntity<ProgramaDTO> save(@Valid @RequestBody ProgramaRequest request) {
        ProgramaDTO savedPrograma = programaService.save(request);
        return ResponseEntity.ok(savedPrograma);
    }

    @PutMapping
    public ResponseEntity<ProgramaDTO> update(@Valid @RequestBody ProgramaRequest request) {
        ProgramaDTO updatedPrograma = programaService.update(request);
        return ResponseEntity.ok(updatedPrograma);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramaDTO> findById(@PathVariable Long id) {
        ProgramaDTO programaDTO = programaService.findById(id);
        return ResponseEntity.ok(programaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        programaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ProgramaDTO>> findAll() {
        List<ProgramaDTO> programas = programaService.findAll();
        return ResponseEntity.ok(programas);
    }
}
