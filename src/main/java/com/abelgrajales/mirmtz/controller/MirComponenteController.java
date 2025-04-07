package com.abelgrajales.mirmtz.controller;

import com.abelgrajales.mirmtz.dto.MirComponenteDTO;
import com.abelgrajales.mirmtz.dto.request.MirActividadComponenteRequest;
import com.abelgrajales.mirmtz.services.MirComponenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mircomponente")
public class MirComponenteController {

    private final MirComponenteService mirComponenteService;

    @Autowired
    public MirComponenteController(MirComponenteService mirComponenteService) {
        this.mirComponenteService = mirComponenteService;
    }

    @PostMapping
    public ResponseEntity<MirComponenteDTO> save(@Valid @RequestBody MirActividadComponenteRequest mirComponenteDTO) {
        MirComponenteDTO savedMirComponente = mirComponenteService.save(mirComponenteDTO);
        return ResponseEntity.ok(savedMirComponente);
    }

    @PutMapping
    public ResponseEntity<MirComponenteDTO> update(@Valid @RequestBody MirActividadComponenteRequest mirComponenteDTO) {
        MirComponenteDTO updatedMirComponente = mirComponenteService.update(mirComponenteDTO);
        return ResponseEntity.ok(updatedMirComponente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MirComponenteDTO> findById(@PathVariable Long id) {
        MirComponenteDTO mirComponenteDTO = mirComponenteService.findById(id);
        return ResponseEntity.ok(mirComponenteDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        mirComponenteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
