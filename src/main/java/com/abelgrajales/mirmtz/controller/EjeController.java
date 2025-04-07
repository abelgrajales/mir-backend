package com.abelgrajales.mirmtz.controller;

import com.abelgrajales.mirmtz.dto.EjeDTO;
import com.abelgrajales.mirmtz.services.EjeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eje")
public class EjeController {

    private final EjeService ejeService;

    @Autowired
    public EjeController(EjeService ejeService) {
        this.ejeService = ejeService;
    }

    @PostMapping
    public ResponseEntity<EjeDTO> save(@Valid @RequestBody EjeDTO ejeDTO) {
        EjeDTO savedEje = ejeService.save(ejeDTO);
        return ResponseEntity.ok(savedEje);
    }

    @PutMapping
    public ResponseEntity<EjeDTO> update(@Valid @RequestBody EjeDTO ejeDTO) {
        EjeDTO updatedEje = ejeService.update(ejeDTO);
        return ResponseEntity.ok(updatedEje);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EjeDTO> findById(@PathVariable Long id) {
        EjeDTO ejeDTO = ejeService.findById(id);
        return ResponseEntity.ok(ejeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        ejeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EjeDTO>> findAll() {
        List<EjeDTO> ejes = ejeService.findAll();
        return ResponseEntity.ok(ejes);
    }

}
