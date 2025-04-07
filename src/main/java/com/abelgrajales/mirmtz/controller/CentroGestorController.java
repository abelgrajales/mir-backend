package com.abelgrajales.mirmtz.controller;

import com.abelgrajales.mirmtz.dto.CentroGestorDTO;
import com.abelgrajales.mirmtz.services.CentroGestorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centro-gestor")
public class CentroGestorController {

    private final CentroGestorService centroGestorService;

    @Autowired
    public CentroGestorController(CentroGestorService centroGestorService) {
        this.centroGestorService = centroGestorService;
    }

    @PostMapping
    public ResponseEntity<CentroGestorDTO> save(@Valid @RequestBody CentroGestorDTO centroGestorDTO) {
        CentroGestorDTO savedCentroGestor = centroGestorService.save(centroGestorDTO);
        return ResponseEntity.ok(savedCentroGestor);
    }

    @PutMapping
    public ResponseEntity<CentroGestorDTO> update(@Valid @RequestBody CentroGestorDTO centroGestorDTO) {
        CentroGestorDTO updatedCentroGestor = centroGestorService.update(centroGestorDTO);
        return ResponseEntity.ok(updatedCentroGestor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CentroGestorDTO> findById(@PathVariable Long id) {
        CentroGestorDTO centroGestorDTO = centroGestorService.findById(id);
        return ResponseEntity.ok(centroGestorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        centroGestorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CentroGestorDTO>> findAll() {
        List<CentroGestorDTO> centroGestores = centroGestorService.findAll();
        return ResponseEntity.ok(centroGestores);
    }
}
