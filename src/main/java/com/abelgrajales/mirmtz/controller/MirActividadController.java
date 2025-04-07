package com.abelgrajales.mirmtz.controller;

import com.abelgrajales.mirmtz.dto.MirActividadDTO;
import com.abelgrajales.mirmtz.dto.request.MirActividadComponenteRequest;
import com.abelgrajales.mirmtz.services.MirActividadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/miractividad")
public class MirActividadController {

    private final MirActividadService mirActividadService;

    @Autowired
    public MirActividadController(MirActividadService mirActividadService) {
        this.mirActividadService = mirActividadService;
    }

    @PostMapping
    public ResponseEntity<MirActividadDTO> save(@Valid @RequestBody MirActividadComponenteRequest request) {
        MirActividadDTO savedMirActividad = mirActividadService.save(request);
        return ResponseEntity.ok(savedMirActividad);
    }

    @PutMapping
    public ResponseEntity<MirActividadDTO> update(@Valid @RequestBody MirActividadComponenteRequest request) {
        MirActividadDTO updatedMirActividad = mirActividadService.update(request);
        return ResponseEntity.ok(updatedMirActividad);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MirActividadDTO> findById(@PathVariable Long id) {
        MirActividadDTO mirActividadDTO = mirActividadService.findById(id);
        return ResponseEntity.ok(mirActividadDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        mirActividadService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
